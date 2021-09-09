package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.blog.BlogDataSource
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.test.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.util.test.CoroutineScopeExtension
import com.ericampire.android.androidstudycase.util.test.MainCoroutineExtension
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExtendWith(
  value = [
    MainCoroutineExtension::class,
    CoroutineDispatcherExtension::class,
    CoroutineScopeExtension::class
  ]
)
class BlogRepositoryTest(
  private val coroutineScope: TestCoroutineScope,
  private val dispatcher: TestCoroutineDispatcher
) {

  // SUT
  private lateinit var blogRepository: BlogRepositoryImpl

  // DOC's
  private val remoteDataSource = mockk<BlogDataSource>(relaxed = true)
  private val localDataSource = mockk<BlogDataSource>(relaxed = true)

  @BeforeEach
  fun setup() {
    blogRepository = BlogRepositoryImpl(
      remoteDataSource = remoteDataSource,
      localDataSource = localDataSource,
      coroutineScope = coroutineScope,
      coroutineDispatcher = dispatcher
    )
  }

  @ExperimentalTime
  @Test
  fun findingAllStories() = runBlockingTest {
    every { remoteDataSource.findAll() } returns flowOf(Result.Success(PreviewData.Blog.data))
    coEvery { localDataSource.save(any()) } just Runs

    blogRepository.findAll().collect {
      assertEquals(it.data, PreviewData.Blog.data)
    }

    verify(exactly = 1) { remoteDataSource.findAll() }
    coVerify(exactly = PreviewData.Blog.data.size) {
      localDataSource.save(any())
    }
  }
}