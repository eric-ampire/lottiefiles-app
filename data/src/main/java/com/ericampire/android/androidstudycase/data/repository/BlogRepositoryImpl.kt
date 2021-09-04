package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.animator.AnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.BlogDataSource
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
  private val remoteDataSource: BlogDataSource,
  private val localDataSource: BlogDataSource,
) : BlogRepository {
  override fun findAll(): Flow<Result<List<Blog>>> {
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