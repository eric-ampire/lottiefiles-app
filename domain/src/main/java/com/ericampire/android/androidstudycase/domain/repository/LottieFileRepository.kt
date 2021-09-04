package com.ericampire.android.androidstudycase.domain.repository

import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow

interface LottieFileRepository {
  fun findRecent(): Flow<Result<List<Lottiefile>>>
  fun findPopular(): Flow<Result<List<Lottiefile>>>
  fun findFeatured(): Flow<Result<List<Lottiefile>>>
}