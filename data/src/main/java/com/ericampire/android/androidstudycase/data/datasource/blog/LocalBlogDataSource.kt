package com.ericampire.android.androidstudycase.data.datasource.blog

import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.domain.entity.Blog
import javax.inject.Inject

class LocalBlogDataSource @Inject constructor(
  private val dao: BlogDao
) : BlogDataSource {
  override suspend fun findAll(): List<Blog> {
    return dao.findAll()
  }

  override suspend fun save(blog: Blog) {
    dao.save(blog)
  }
}