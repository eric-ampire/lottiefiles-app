package com.ericampire.android.androidstudycase.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig

class DownloaderInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    val config = PRDownloaderConfig.newBuilder()
      .setDatabaseEnabled(true)
      .build()
    PRDownloader.initialize(context, config)
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> {
    return mutableListOf()
  }
}