package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.blog.BlogDataSource
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
  private val remoteDataSource: BlogDataSource,
  private val localDataSource: BlogDataSource,
) : BlogRepository {
  override fun findAll(): Flow<Result<List<Blog>>> {
    refreshData()
    return localDataSource.findAll()
  }

  private fun refreshData() {
    suspend {
      remoteDataSource.findAll().collect {
        if (it is Result.Success) {
          it.data.forEach { blog ->
            localDataSource.save(blog)
          }
        }
      }
    }
  }
}