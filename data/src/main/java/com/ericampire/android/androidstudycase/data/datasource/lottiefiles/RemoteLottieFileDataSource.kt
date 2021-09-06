package com.ericampire.android.androidstudycase.data.datasource.lottiefiles

import com.ericampire.android.androidstudycase.domain.entity.LottieFilesApiResponse
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.util.ApiUrl
import com.ericampire.android.androidstudycase.util.Result
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteLottieFileDataSource @Inject constructor(
  private val httpClient: HttpClient
) : LottieFileDataSource {

  private fun find(url: String): Flow<Result<List<Lottiefile>>> {
    return flow {
      try {
        val data = httpClient.get<LottieFilesApiResponse>(url)
        emit(Result.Success(data.lottieFilesLottieFilesData.page.results))
      } catch (e: Exception) {
        emit(Result.Error(e))
      }
    }
  }

  override fun findRecent(): Flow<Result<List<Lottiefile>>> {
    return find(ApiUrl.LottieFile.recent)
  }

  override fun findPopular(): Flow<Result<List<Lottiefile>>> {
    return find(ApiUrl.LottieFile.popular)
  }

  override fun findFeatured(): Flow<Result<List<Lottiefile>>> {
    return find(ApiUrl.LottieFile.featured)
  }

  override suspend fun save(lottiefile: Lottiefile) {
    TODO("Not supported by the API")
  }
}