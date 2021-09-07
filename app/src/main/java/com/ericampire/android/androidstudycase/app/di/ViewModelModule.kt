package com.ericampire.android.androidstudycase.app.di

import com.ericampire.android.androidstudycase.app.hilt.AssistedViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.MavericksViewModelComponent
import com.ericampire.android.androidstudycase.app.hilt.ViewModelKey
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewModel
import com.ericampire.android.androidstudycase.presentation.screen.home.business.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  fun provideHomeViewModel(
    factory: HomeViewModel.Factory
  ): AssistedViewModelFactory<*, *>

  @Binds
  @IntoMap
  @ViewModelKey(ExploreViewModel::class)
  fun provideExploreViewModel(
    factory: ExploreViewModel.Factory
  ): AssistedViewModelFactory<*, *>
}