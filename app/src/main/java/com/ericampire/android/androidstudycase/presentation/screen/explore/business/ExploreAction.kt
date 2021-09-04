package com.ericampire.android.androidstudycase.presentation.screen.explore.business

sealed interface ExploreAction {
  object FindRecentFile : ExploreAction
  object FindFeaturedFile : ExploreAction
  object FindPopularFile : ExploreAction
}