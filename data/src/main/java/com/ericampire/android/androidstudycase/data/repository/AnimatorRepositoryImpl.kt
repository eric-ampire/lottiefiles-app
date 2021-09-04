package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.animator.AnimatorDataSource
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AnimatorRepositoryImpl @Inject constructor(
  private val remoteDataSource: AnimatorDataSource,
  private val localDataSource: AnimatorDataSource,
) : AnimatorRepository {
  override fun findAll(): Flow<Result<List<Animator>>> {
    return flow {
      try {
        refreshData()
        emit(Result.Success(localDataSource.findAll()))
      } catch (e: Exception) {
        emit(Result.Error(e))
      }
    }
  }

  private suspend fun refreshData() {
    remoteDataSource.findAll().forEach {
      localDataSource.save(it)
    }
  }
}