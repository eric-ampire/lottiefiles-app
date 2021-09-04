package com.ericampire.android.androidstudycase.data.datasource.lottiefiles

import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile

interface LottieFileDataSource {
  suspend fun findRecent(): List<Lottiefile>
  suspend fun findPopular(): List<Lottiefile>
  suspend fun findFeatured(): List<Lottiefile>
  suspend fun save(lottiefile: Lottiefile)
}