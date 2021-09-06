package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LottieFileDataSource
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class LottieFileRepositoryImpl @Inject constructor(
  private val localDataSource: LottieFileDataSource,
  private val remoteDataSource: LottieFileDataSource,
) : LottieFileRepository {

  override fun findRecent(): Flow<Result<List<Lottiefile>>> {
    val recentFiles = remoteDataSource.findRecent()
    refreshData(recentFiles)
    return recentFiles
  }

  private fun refreshData(data: Flow<Result<List<Lottiefile>>>) {
    suspend {
      data.collect {
        if (it is Result.Success) {
          it.data.forEach { file -> localDataSource.save(file) }
        }
      }
    }
  }

  override fun findPopular(): Flow<Result<List<Lottiefile>>> {
    val files = remoteDataSource.findPopular()
    refreshData(files)
    return files
  }

  override fun findFeatured(): Flow<Result<List<Lottiefile>>> {
    val files = remoteDataSource.findFeatured()
    refreshData(files)
    return files
  }
}