package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LottieFileDataSource
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LottieFileRepositoryImpl @Inject constructor(
  private val localDataSource: LottieFileDataSource,
  private val remoteDataSource: LottieFileDataSource,
) : LottieFileRepository {
  override suspend fun findRecent(): Flow<Result<List<Lottiefile>>> {
    return flow {
      try {
        val recentFiles = remoteDataSource.findRecent()
        refreshData(recentFiles)
        emit(Result.Success(localDataSource.findRecent()))
      } catch (e: Exception) {
        emit(Result.Error(e))
      }
    }
  }

  private suspend fun refreshData(data: List<Lottiefile>) {
    data.forEach {
      localDataSource.save(it)
    }
  }

  override suspend fun findPopular(): Flow<Result<List<Lottiefile>>> {
    return flow {
      try {
        val recentFiles = remoteDataSource.findPopular()
        refreshData(recentFiles)
        emit(Result.Success(localDataSource.findPopular()))
      } catch (e: Exception) {
        emit(Result.Error(e))
      }
    }
  }

  override suspend fun findFeatured(): Flow<Result<List<Lottiefile>>> {
    return flow {
      try {
        val recentFiles = remoteDataSource.findFeatured()
        refreshData(recentFiles)
        emit(Result.Success(localDataSource.findFeatured()))
      } catch (e: Exception) {
        emit(Result.Error(e))
      }
    }
  }
}