package com.ericampire.android.androidstudycase.presentation.screen.home.business

sealed interface HomeAction {
  object FetchData : HomeAction
  object Login : HomeAction
  object FetchCurrentUser : HomeAction
}