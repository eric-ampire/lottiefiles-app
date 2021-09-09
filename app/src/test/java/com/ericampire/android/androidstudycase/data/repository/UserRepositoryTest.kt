package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.user.UserDataSource
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

@ExperimentalCoroutinesApi
@ExtendWith(
  value = [
    MainCoroutineExtension::class,
    CoroutineDispatcherExtension::class,
    CoroutineScopeExtension::class
  ]
)
class UserRepositoryTest(
  private val coroutineScope: TestCoroutineScope,
  private val dispatcher: TestCoroutineDispatcher
) {
  // SUT
  private lateinit var repository: UserRepositoryImpl

  // DOC's
  private val localDataSource = mockk<UserDataSource>(relaxed = true)

  @BeforeEach
  fun setup() {
    repository = UserRepositoryImpl(localDataSource)
  }

  @Test
  fun findCurrentUser() = runBlockingTest {
    every { localDataSource.findAll() } returns flowOf(Result.Success(PreviewData.User.data))

    repository.findAll().collect {
      assertEquals(it.data, PreviewData.User.data)
    }

    verify(exactly = 1) { localDataSource.findAll() }
  }

  @Test
  fun saveUser() = runBlockingTest {
    coEvery { localDataSource.save(PreviewData.User.data.first()) } just Runs

    repository.save(PreviewData.User.data.first())

    coVerify(exactly = 1) {
      localDataSource.save(PreviewData.User.data.first())
    }
  }
}