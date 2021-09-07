package com.ericampire.android.androidstudycase.presentation.screen.home.business

sealed interface HomeAction {
  object FetchData : HomeAction
}