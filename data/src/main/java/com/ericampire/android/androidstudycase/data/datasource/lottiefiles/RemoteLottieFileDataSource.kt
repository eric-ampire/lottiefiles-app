package com.ericampire.android.androidstudycase.data.datasource.lottiefiles

import com.ericampire.android.androidstudycase.domain.entity.LottieFilesApiResponse
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.util.ApiUrl
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class RemoteLottieFileDataSource @Inject constructor(
  private val httpClient: HttpClient
) : LottieFileDataSource {

  private suspend fun find(url: String): List<Lottiefile> {
    val data = httpClient.get<LottieFilesApiResponse>(url)
    return data.lottieFilesLottieFilesData.page.results
  }

  override suspend fun findRecent(): List<Lottiefile> {
    return find(ApiUrl.LottieFile.recent)
  }

  override suspend fun findPopular(): List<Lottiefile> {
    return find(ApiUrl.LottieFile.popular)
  }

  override suspend fun findFeatured(): List<Lottiefile> {
    return find(ApiUrl.LottieFile.featured)
  }

  override suspend fun save(lottiefile: Lottiefile) {
    TODO("Not supported by the API")
  }
}