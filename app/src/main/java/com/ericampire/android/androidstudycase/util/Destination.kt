package com.ericampire.android.androidstudycase.util

import androidx.annotation.StringRes
import com.ericampire.android.androidstudycase.R

sealed class Destination(val route: String, @StringRes val resourceId: Int) {
  object Home : Destination("home", org.zxconnect.android.beserve.i18n.R.string.txt_home)
  object Explore : Destination("explore", org.zxconnect.android.beserve.i18n.R.string.txt_explore)
  object Preview : Destination("preview", org.zxconnect.android.beserve.i18n.R.string.txt_preview)
  object Login : Destination("login", org.zxconnect.android.beserve.i18n.R.string.txt_login)
}