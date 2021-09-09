package com.ericampire.android.androidstudycase.domain.user

import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import com.ericampire.android.androidstudycase.domain.usecase.SaveUserUseCase
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.test.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.util.test.MainCoroutineExtension
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(value = [MainCoroutineExtension::class, CoroutineDispatcherExtension::class])
class SaveUserUseCaseTest(
  private val testCoroutineDisabled: TestCoroutineDispatcher
) {

  // SUT
  private lateinit var useCase: SaveUserUseCase

  // DOC's
  private val repository = mockk<UserRepository>(relaxed = true)

  @BeforeEach
  fun setUp() {
    useCase = SaveUserUseCase(
      dispatcher = testCoroutineDisabled,
      repository = repository
    )
  }

  @Test
  fun saveUser() = runBlockingTest {
    coEvery { repository.save(eq(PreviewData.User.data.first())) } just Runs
    useCase.invoke(PreviewData.User.data.first())
    coVerify(exactly = 1) {
      repository.save(eq(PreviewData.User.data.first()))
    }
  }
}