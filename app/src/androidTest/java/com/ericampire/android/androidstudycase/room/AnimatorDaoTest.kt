package com.ericampire.android.androidstudycase.room

import app.cash.turbine.test
import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.room.common.DatabaseTest
import com.ericampire.android.androidstudycase.util.PreviewData
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class AnimatorDoaTest : DatabaseTest() {

  private lateinit var animatorDao: AnimatorDao

  @BeforeEach
  fun setup() {
    animatorDao = db.animatorDao
  }

  @Test
  fun saveAnimator() = runBlocking {
    val testData = PreviewData.Animator.data.first().copy(id = 1)
    animatorDao.save(testData)
    animatorDao.findAll().test {
      Assertions.assertEquals(awaitItem().first(), testData)
    }
  }
}