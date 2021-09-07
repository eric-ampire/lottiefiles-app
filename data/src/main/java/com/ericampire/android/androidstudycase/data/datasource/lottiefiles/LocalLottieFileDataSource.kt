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
  override fun findRecent(): Flow<Result<List<Lottiefile>>> {
    return lottieFileDao.findByType("recent").map {
      Result.Success(it)
    }
  }

  override fun findPopular(): Flow<Result<List<Lottiefile>>> {
    return lottieFileDao.findByType("popular").map {
      Result.Success(it)
    }
  }

  override fun findFeatured(): Flow<Result<List<Lottiefile>>> {
    return lottieFileDao.findByType("featured").map {
      Result.Success(it)
    }
  }

  override suspend fun save(lottiefile: Lottiefile) {
    lottieFileDao.save(lottiefile)
  }
}