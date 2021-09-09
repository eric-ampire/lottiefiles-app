package com.ericampire.android.androidstudycase.room

import app.cash.turbine.test
import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.room.common.DatabaseTest
import com.ericampire.android.androidstudycase.util.PreviewData
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class AnimationDoaTest : DatabaseTest() {

  private lateinit var animationDao: LottieFilesDao

  @BeforeEach
  fun setup() {
    animationDao = db.lottieFileDao
  }


  @Test
  fun saveAnimation() = runBlocking {
    val animation = PreviewData.Lottiefile.data.first().copy(type = "recent")
    animationDao.save(animation)
    animationDao.findByType("recent").test {
      Assertions.assertEquals(awaitItem().first(), animation)
    }
  }
}