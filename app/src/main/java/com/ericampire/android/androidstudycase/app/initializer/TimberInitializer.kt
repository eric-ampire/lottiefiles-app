package com.ericampire.android.androidstudycase.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.ericampire.android.androidstudycase.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> {
    return mutableListOf()
  }
}