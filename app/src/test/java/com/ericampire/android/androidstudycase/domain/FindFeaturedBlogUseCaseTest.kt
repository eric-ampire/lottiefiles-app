package com.ericampire.android.androidstudycase.domain

import com.ericampire.android.androidstudycase.common.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.common.MainCoroutineExtension
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedBlogUseCase
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.data
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(value = [MainCoroutineExtension::class, CoroutineDispatcherExtension::class])
internal class FindFeaturedBlogUseCaseTest(
  private val testCoroutineDisabled: TestCoroutineDispatcher
) {

  // SUT
  private lateinit var useCase: FindFeaturedBlogUseCase

  // DOC's
  private val repository = mockk<BlogRepository>(relaxed = true)

  @BeforeEach
  fun setUp() {
    useCase = FindFeaturedBlogUseCase(
      dispatcher = testCoroutineDisabled,
      repository = repository
    )
  }

  @Test
  fun findFeaturedAnimation() = runBlockingTest {
    every { repository.findAll() } returns flow {
      emit(Result.Success(PreviewData.Blog.data))
    }
    useCase.invoke(Unit).collect {
      Assertions.assertEquals(it.data, PreviewData.Blog.data)
    }

    verify(exactly = 1) {
      repository.findAll()
    }
  }
}