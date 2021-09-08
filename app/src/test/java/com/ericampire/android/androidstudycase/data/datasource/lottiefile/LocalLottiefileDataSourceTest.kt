package com.ericampire.android.androidstudycase.data.datasource.lottiefile

import com.ericampire.android.androidstudycase.common.MainCoroutineExtension
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LocalLottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LottieFileDataSource
import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.data
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
class LocalLottiefileDataSourceTest {
  // SUT
  private lateinit var dataSource: LottieFileDataSource

  // DOC's
  private val filesDao = mockk<LottieFilesDao>(relaxed = true)

  @BeforeEach
  fun setup() {
    dataSource = LocalLottieFileDataSource(filesDao)
  }

  @Test
  fun findFeatured() = runBlockingTest {
    every { filesDao.findByType(eq("featured")) } returns flowOf(PreviewData.Lottiefile.data)

    dataSource.findFeatured().collect {
      Assertions.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) {
      filesDao.findByType(eq("featured"))
    }
  }

  @Test
  fun findRecent() = runBlockingTest {
    every { filesDao.findByType(eq("recent")) } returns flowOf(PreviewData.Lottiefile.data)

    dataSource.findRecent().collect {
      Assertions.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) {
      filesDao.findByType(eq("recent"))
    }
  }

  @Test
  fun findPopular() = runBlockingTest {
    every { filesDao.findByType(eq("popular")) } returns flowOf(PreviewData.Lottiefile.data)

    dataSource.findPopular().collect {
      Assertions.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) {
      filesDao.findByType(eq("popular"))
    }
  }

  @Test
  fun saveUser() = runBlockingTest {
    coEvery { filesDao.save(PreviewData.Lottiefile.data.first()) } just Runs
    dataSource.save(PreviewData.Lottiefile.data.first())
    coVerify {
      filesDao.save(eq(PreviewData.Lottiefile.data.first()))
    }
  }
}