package com.ericampire.android.androidstudycase.domain

import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedAnimatorUseCase
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.test.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.util.test.MainCoroutineExtension
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
class FindFeaturedAnimatorUseCaseTest(
  private val testCoroutineDisabled: TestCoroutineDispatcher
) {
  // SUT
  private lateinit var useCase: FindFeaturedAnimatorUseCase

  // DOC's
  private val repository = mockk<AnimatorRepository>(relaxed = true)

  @BeforeEach
  fun setUp() {
    useCase = FindFeaturedAnimatorUseCase(
      dispatcher = testCoroutineDisabled,
      repository = repository
    )
  }

  @Test
  fun findFeaturedAnimator() = runBlockingTest {
    every { repository.findAll() } returns flow {
      emit(Result.Success(PreviewData.Animator.data))
    }
    useCase.invoke(Unit).collect {
      Assertions.assertEquals(it.data, PreviewData.Animator.data)
    }

    verify(exactly = 1) {
      repository.findAll()
    }
  }
}