package com.ericampire.android.androidstudycase.presentation.screen.home.business

import com.ericampire.android.androidstudycase.domain.entity.Blog

data class HomeViewState(
  val blog: List<Blog> = emptyList()
)
