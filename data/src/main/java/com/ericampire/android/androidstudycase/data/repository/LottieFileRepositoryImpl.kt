package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LottieFileDataSource
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LottieFileRepositoryImpl @Inject constructor(
  private val localDataSource: LottieFileDataSource,
  private val remoteDataSource: LottieFileDataSource,
  private val coroutineScope: CoroutineScope,
  @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : LottieFileRepository {

  override fun findRecent(): Flow<Result<List<Lottiefile>>> {
    refreshData(remoteDataSource.findRecent(), "recent")
    return localDataSource.findRecent()
  }

  private fun refreshData(data: Flow<Result<List<Lottiefile>>>, type: String) {
    coroutineScope.launch(coroutineDispatcher) {
      data.collect {
        if (it is Result.Success) {
          it.data.forEach { file -> localDataSource.save(file.copy(type = type)) }
        }
      }
    }
  }

  override fun findPopular(): Flow<Result<List<Lottiefile>>> {
    refreshData(remoteDataSource.findPopular(), "popular")
    return localDataSource.findPopular()
  }

  override fun findFeatured(): Flow<Result<List<Lottiefile>>> {
    refreshData(remoteDataSource.findFeatured(), "featured")
    return localDataSource.findFeatured()
  }
}