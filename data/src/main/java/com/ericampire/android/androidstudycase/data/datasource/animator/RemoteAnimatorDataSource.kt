package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.AnimatorApiResponse
import com.ericampire.android.androidstudycase.util.ApiUrl
import com.ericampire.android.androidstudycase.util.Result
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteAnimatorDataSource @Inject constructor(
  private val httpClient: HttpClient
) : AnimatorDataSource {
  override suspend fun findAll(): List<Animator> {
    val data = httpClient.get<AnimatorApiResponse>(ApiUrl.Animator.featured)
    return data.animatorAnimatorData.featuredAnimators.results
  }

  override suspend fun save(animator: Animator) {
    TODO("Save animator online, not supported by the API")
  }
}