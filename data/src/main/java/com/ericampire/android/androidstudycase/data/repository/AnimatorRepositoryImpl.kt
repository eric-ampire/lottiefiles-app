package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.animator.AnimatorDataSource
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class AnimatorRepositoryImpl @Inject constructor(
  private val remoteDataSource: AnimatorDataSource,
  private val localDataSource: AnimatorDataSource,
  private val coroutineScope: CoroutineScope,
  @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : AnimatorRepository {

  override fun findAll(): Flow<Result<List<Animator>>> {
    refreshData()
    return localDataSource.findAll()
  }

  private fun refreshData() {
    coroutineScope.launch(coroutineDispatcher) {
      remoteDataSource.findAll().collect {
        if (it is Result.Success) {
          it.data.forEach { animator ->
            localDataSource.save(animator)
          }
        }
      }
    }
  }
}