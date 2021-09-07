package com.ericampire.android.androidstudycase.util

import androidx.annotation.StringRes
import com.ericampire.android.androidstudycase.R

sealed class Destination(val route: String, @StringRes val resourceId: Int) {
  object Home : Destination("home", R.string.txt_home)
  object Explore : Destination("explore", R.string.txt_explore)
  object Preview : Destination("preview", R.string.txt_preview)
  object Login : Destination("login", R.string.txt_login)
}