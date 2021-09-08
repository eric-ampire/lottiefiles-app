package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.common.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.common.CoroutineScopeExtension
import com.ericampire.android.androidstudycase.common.MainCoroutineExtension
import com.ericampire.android.androidstudycase.data.datasource.animator.AnimatorDataSource
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.data
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
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
class AnimatorRepositoryTest(
  private val coroutineScope: TestCoroutineScope,
  private val dispatcher: TestCoroutineDispatcher
) {

  // SUD
  private lateinit var repository: AnimatorRepository

  // DOC's
  private val remoteDataSource = mockk<AnimatorDataSource>(relaxed = true)
  private val localDataSource = mockk<AnimatorDataSource>(relaxed = true)

  @BeforeEach
  fun setup() {
    repository = AnimatorRepositoryImpl(
      remoteDataSource = remoteDataSource,
      localDataSource = localDataSource,
      coroutineScope = coroutineScope,
      coroutineDispatcher = dispatcher
    )
  }

  @ExperimentalTime
  @Test
  fun findingAllAnimator() = runBlockingTest {
    every { remoteDataSource.findAll() } returns flowOf(Result.Success(PreviewData.Animator.data))
    coEvery { localDataSource.save(any()) } just Runs

    repository.findAll().collect {
      assertEquals(it.data, PreviewData.Animator.data)
    }

    coVerify {
      remoteDataSource.findAll()
      localDataSource.save(any())
    }
  }
}