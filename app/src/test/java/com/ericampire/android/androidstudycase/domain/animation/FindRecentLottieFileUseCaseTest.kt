package com.ericampire.android.androidstudycase.domain.animation

import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.domain.usecase.FindRecentLottieFileUseCase
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
class FindRecentLottieFileUseCaseTest(
  private val testCoroutineDisabled: TestCoroutineDispatcher
) {

  // SUT
  private lateinit var useCase: FindRecentLottieFileUseCase

  // DOC's
  private val repository = mockk<LottieFileRepository>(relaxed = true)

  @BeforeEach
  fun setUp() {
    useCase = FindRecentLottieFileUseCase(
      dispatcher = testCoroutineDisabled,
      repository = repository
    )
  }

  @Test
  fun findRecentAnimations() = runBlockingTest {
    every { repository.findRecent() } returns flow {
      emit(Result.Success(PreviewData.Lottiefile.data))
    }
    useCase.invoke(Unit).collect {
      Assertions.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) {
      repository.findRecent()
    }
  }
}