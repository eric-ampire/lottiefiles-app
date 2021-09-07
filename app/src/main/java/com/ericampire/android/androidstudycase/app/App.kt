package com.ericampire.android.androidstudycase.app

import android.app.Application
import android.content.Context
import android.content.Intent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
  override fun onCreate() {
    super.onCreate()
  }

  companion object {
    fun restart(context: Context) {
      context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addCategory(Intent.CATEGORY_HOME)
        context.startActivity(this)
      }
    }
  }
}