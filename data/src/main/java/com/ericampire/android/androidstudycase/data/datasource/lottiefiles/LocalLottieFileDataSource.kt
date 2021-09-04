package com.ericampire.android.androidstudycase.data.datasource.lottiefiles

import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import javax.inject.Inject

class LocalLottieFileDataSource @Inject constructor(
  private val lottieFileDao: LottieFilesDao
) : LottieFileDataSource {
  override suspend fun findRecent(): List<Lottiefile> {
    return lottieFileDao.findRecent()
  }

  override suspend fun findPopular(): List<Lottiefile> {
    return lottieFileDao.findPopular()
  }

  override suspend fun findFeatured(): List<Lottiefile> {
    return lottieFileDao.findFeatured()
  }

  override suspend fun save(lottiefile: Lottiefile) {
    lottieFileDao.save(lottiefile)
  }
}