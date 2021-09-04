package com.ericampire.android.androidstudycase.presentation.screen.explore.business

import com.ericampire.android.androidstudycase.domain.entity.Lottiefile

data class ExploreViewState(
  val files: List<Lottiefile> = emptyList(),
  val isLoading: Boolean = false
)
