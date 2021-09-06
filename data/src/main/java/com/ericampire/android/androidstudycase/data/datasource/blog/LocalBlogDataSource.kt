package com.ericampire.android.androidstudycase.data.datasource.blog

import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalBlogDataSource @Inject constructor(
  private val dao: BlogDao
) : BlogDataSource {
  override fun findAll(): Flow<Result<List<Blog>>> {
    return dao.findAll().map {
      Result.Success(it)
    }
  }

  override suspend fun save(blog: Blog) {
    dao.save(blog)
  }
}