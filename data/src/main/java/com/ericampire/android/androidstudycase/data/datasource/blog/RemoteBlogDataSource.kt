package com.ericampire.android.androidstudycase.data.datasource.blog

import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.BlogApiResponse
import com.ericampire.android.androidstudycase.util.ApiUrl
import com.ericampire.android.androidstudycase.util.Result
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteBlogDataSource @Inject constructor(
  private val httpClient: HttpClient
) : BlogDataSource {
  override fun findAll(): Flow<Result<List<Blog>>> {
    return flow {
      try {
        val data = httpClient.get<BlogApiResponse>(ApiUrl.Blog.latest)
        val identifiableData = data.data.blogs.results.mapIndexed { index, blog ->
          blog.copy(id = index.toLong())
        }
        emit(Result.Success(identifiableData))
      } catch (e: Exception) {
        emit(Result.Error(e))
      }
    }
  }

  override suspend fun save(blog: Blog) {
    TODO("Not supported yet by the API")
  }
}