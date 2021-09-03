package com.ericampire.android.androidstudycase.app.di


import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.MainDispatcher
import com.ericampire.android.androidstudycase.util.MainImmediateDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

  @Provides
  @IoDispatcher
  fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  @MainDispatcher
  fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

  @Provides
  @MainImmediateDispatcher
  fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}