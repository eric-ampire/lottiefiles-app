package com.ericampire.android.androidstudycase.room

import app.cash.turbine.test
import com.ericampire.android.androidstudycase.data.room.UserDao
import com.ericampire.android.androidstudycase.room.common.DatabaseTest
import com.ericampire.android.androidstudycase.util.PreviewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class UserDoaTest : DatabaseTest() {

  private lateinit var userDao: UserDao

  @BeforeEach
  fun setup() {
    userDao = db.userDao
  }

  @Test
  fun saveUser() = runBlocking(Dispatchers.IO) {
    userDao.save(PreviewData.User.data.first())
    userDao.findAll().test {
      assertEquals(awaitItem().first(), PreviewData.User.data.first())
    }
  }
}