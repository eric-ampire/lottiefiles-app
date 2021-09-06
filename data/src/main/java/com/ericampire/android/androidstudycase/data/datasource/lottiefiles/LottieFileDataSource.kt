package com.ericampire.android.androidstudycase.data.datasource.lottiefiles

import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow

interface LottieFileDataSource {
  suspend fun findRecent(): Flow<Result<List<Lottiefile>>>
  suspend fun findPopular(): Flow<Result<List<Lottiefile>>>
  suspend fun findFeatured(): Flow<Result<List<Lottiefile>>>
  suspend fun save(lottiefile: Lottiefile)
}