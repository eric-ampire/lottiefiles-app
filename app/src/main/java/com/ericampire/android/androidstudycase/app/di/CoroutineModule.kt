package com.ericampire.android.androidstudycase.app.di


import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.MainDispatcher
import com.ericampire.android.androidstudycase.util.MainImmediateDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

  @Provides
  fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())

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