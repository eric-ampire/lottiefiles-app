package com.ericampire.android.androidstudycase.app.di

import com.ericampire.android.androidstudycase.domain.usecase.*
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewModel
import com.ericampire.android.androidstudycase.presentation.screen.home.business.HomeViewModel
import com.ericampire.android.androidstudycase.presentation.screen.preview.business.PreviewViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

  @Provides
  fun provideHomeViewModel(
    findFeaturedAnimatorUseCase: FindFeaturedAnimatorUseCase,
    findFeaturedBlogUseCase: FindFeaturedBlogUseCase
  ): HomeViewModel {
    return HomeViewModel(
      findFeaturedAnimatorUseCase = findFeaturedAnimatorUseCase,
      findFeaturedBlogUseCase = findFeaturedBlogUseCase
    )
  }

  @Provides
  fun provideExploreViewModel(
    findFeaturedAnimatorUseCase: FindFeaturedAnimatorUseCase,
    findFeaturedBlogUseCase: FindFeaturedBlogUseCase
  ): PreviewViewModel {
    return PreviewViewModel()
  }

  @Provides
  fun providePreview(
    findPopularLottieFileUseCase: FindPopularLottieFileUseCase,
    findRecentLottieFileUseCase: FindRecentLottieFileUseCase,
    findFeaturedLottieFileUseCase: FindFeaturedLottieFileUseCase
  ): ExploreViewModel {
    return ExploreViewModel(
      findPopularLottieFileUseCase = findPopularLottieFileUseCase,
      findRecentLottieFileUseCase = findRecentLottieFileUseCase,
      findFeaturedLottieFileUseCase = findFeaturedLottieFileUseCase
    )
  }
}