package com.ericampire.android.androidstudycase.data.datasource.user

import com.ericampire.android.androidstudycase.data.room.UserDao
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.test.MainCoroutineExtension
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MainCoroutineExtension::class)
class LocalUserDataSourceTest {

  // SUT
  private lateinit var dataSource: UserDataSource

  // DOC's
  private val userDao = mockk<UserDao>(relaxed = true)

  @BeforeEach
  fun setup() {
    dataSource = LocalUserDataSource(userDao)
  }

  @Test
  fun findAllUsers() = runBlockingTest {
    every { userDao.findAll() } returns flowOf(PreviewData.User.data)

    dataSource.findAll().collect {
      Assertions.assertEquals(it.data, PreviewData.User.data)
    }

    verify(exactly = 1) {
      userDao.findAll()
    }
  }

  @Test
  fun saveUser() = runBlockingTest {
    coEvery { userDao.save(PreviewData.User.data.first()) } just Runs
    dataSource.save(PreviewData.User.data.first())
    coVerify {
      userDao.save(eq(PreviewData.User.data.first()))
    }
  }
}