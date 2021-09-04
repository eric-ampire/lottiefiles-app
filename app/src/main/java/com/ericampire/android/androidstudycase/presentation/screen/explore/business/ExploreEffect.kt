package com.ericampire.android.androidstudycase.presentation.screen.explore.business

sealed interface ExploreEffect {
  data class ShowErrorMessage(val message: String) : ExploreEffect
  object Loading : ExploreEffect
  object Success : ExploreEffect
}