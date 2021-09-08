package com.ericampire.android.androidstudycase.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.ericampire.android.androidstudycase.app.room.AppDatabase
import com.ericampire.android.androidstudycase.data.room.UserDao
import com.ericampire.android.androidstudycase.util.PreviewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.time.ExperimentalTime

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@ExperimentalTime
class UserDaoTest {

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)

  private lateinit var userDao: UserDao
  private lateinit var db: AppDatabase

  @Before
  fun setupDispatcher() {
    Dispatchers.setMain(testDispatcher)
  }


  @Before
  fun createDb() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    userDao = db.userDao
  }

  @Test
  fun saveUser() = testScope.runBlockingTest {
    userDao.save(PreviewData.User.data.first())
    userDao.findAll().test {
      assertThat(awaitItem(), equalTo(PreviewData.User.data.first()))
    }
  }

  @After
  @Throws(IOException::class)
  fun teardown() {
    db.close()
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }
}