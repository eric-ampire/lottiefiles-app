package com.ericampire.android.androidstudycase.domain.user

import com.ericampire.android.androidstudycase.common.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.common.MainCoroutineExtension
import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import com.ericampire.android.androidstudycase.domain.usecase.FindUsersUseCase
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
class FindUserUseCaseTest(
  private val testCoroutineDisabled: TestCoroutineDispatcher
) {

  // SUT
  private lateinit var useCase: FindUsersUseCase

  // DOC's
  private val repository = mockk<UserRepository>(relaxed = true)

  @BeforeEach
  fun setUp() {
    useCase = FindUsersUseCase(
      dispatcher = testCoroutineDisabled,
      repository = repository
    )
  }

  @Test
  fun findCurrentUser() = runBlockingTest {
    every { repository.findAll() } returns flow {
      emit(Result.Success(PreviewData.User.data))
    }
    useCase.invoke(Unit).collect {
      Assertions.assertEquals(it.data, PreviewData.User.data)
    }
    verify(exactly = 1) {
      repository.findAll()
    }
  }
}