package com.ericampire.android.androidstudycase.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideSharedPreference(
    @ApplicationContext context: Context
  ): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
  }
}