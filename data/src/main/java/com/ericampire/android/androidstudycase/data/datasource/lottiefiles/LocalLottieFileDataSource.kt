package com.ericampire.android.androidstudycase.data.datasource.lottiefiles

import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalLottieFileDataSource @Inject constructor(
  private val lottieFileDao: LottieFilesDao
) : LottieFileDataSource {
  override suspend fun findRecent(): Flow<Result<List<Lottiefile>>> {
    return lottieFileDao.findRecent().map {
      Result.Success(it)
    }
  }

  override suspend fun findPopular(): Flow<Result<List<Lottiefile>>> {
    return lottieFileDao.findPopular().map {
      Result.Success(it)
    }
  }

  override suspend fun findFeatured(): Flow<Result<List<Lottiefile>>> {
    return lottieFileDao.findFeatured().map {
      Result.Success(it)
    }
  }

  override suspend fun save(lottiefile: Lottiefile) {
    lottieFileDao.save(lottiefile)
  }
}