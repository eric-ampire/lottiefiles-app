package com.ericampire.android.androidstudycase.data.datasource.blog

import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.BlogApiResponse
import com.ericampire.android.androidstudycase.util.ApiUrl
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class RemoteBlogDataSource @Inject constructor(
  private val httpClient: HttpClient
) : BlogDataSource {
  override suspend fun findAll(): List<Blog> {
    val data = httpClient.get<BlogApiResponse>(ApiUrl.Blog.latest)
    return data.blogBlogData.blogPage.blogs
  }

  override suspend fun save(blog: Blog) {
    TODO("Not supported yet by the API")
  }
}