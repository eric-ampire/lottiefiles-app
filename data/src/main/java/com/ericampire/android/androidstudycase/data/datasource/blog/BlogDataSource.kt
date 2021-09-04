package com.ericampire.android.androidstudycase.data.datasource.blog


import com.ericampire.android.androidstudycase.domain.entity.Blog

interface BlogDataSource {
  suspend fun findAll(): List<Blog>
  suspend fun save(blog: Blog)
}