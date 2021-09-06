package com.ericampire.android.androidstudycase.data.datasource.blog


import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow

interface BlogDataSource {
  fun findAll(): Flow<Result<List<Blog>>>
  suspend fun save(blog: Blog)
}