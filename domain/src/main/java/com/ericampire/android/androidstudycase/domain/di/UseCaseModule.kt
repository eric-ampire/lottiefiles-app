package com.ericampire.android.androidstudycase.domain.di

import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import com.ericampire.android.androidstudycase.domain.usecase.*
import com.ericampire.android.androidstudycase.util.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  fun provideAnimatorUseCase(
    repository: AnimatorRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): FindFeaturedAnimatorUseCase {
    return FindFeaturedAnimatorUseCase(repository, dispatcher)
  }

  @Provides
  fun provideSaveUserUseCase(
    repository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): SaveUserUseCase {
    return SaveUserUseCase(repository, dispatcher)
  }

  @Provides
  fun provideFindUsersUseCase(
    repository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): FindUsersUseCase {
    return FindUsersUseCase(repository, dispatcher)
  }

  @Provides
  fun provideFindFeaturedBlogUseCase(
    repository: BlogRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): FindFeaturedBlogUseCase {
    return FindFeaturedBlogUseCase(repository, dispatcher)
  }

  @Provides
  fun provideFindFeaturedLottieFileUseCase(
    repository: LottieFileRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): FindFeaturedLottieFileUseCase {
    return FindFeaturedLottieFileUseCase(repository, dispatcher)
  }

  @Provides
  fun provideFindRecentLottieFileUseCase(
    repository: LottieFileRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): FindRecentLottieFileUseCase {
    return FindRecentLottieFileUseCase(repository, dispatcher)
  }

  @Provides
  fun provideFindPopularLottieFileUseCase(
    repository: LottieFileRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): FindPopularLottieFileUseCase {
    return FindPopularLottieFileUseCase(repository, dispatcher)
  }
}