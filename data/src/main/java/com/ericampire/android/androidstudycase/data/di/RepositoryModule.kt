package com.ericampire.android.androidstudycase.data.di

import com.ericampire.android.androidstudycase.data.datasource.animator.LocalAnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.animator.RemoteAnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.LocalBlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.RemoteBlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LocalLottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.RemoteLottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.user.LocalUserDataSource
import com.ericampire.android.androidstudycase.data.repository.AnimatorRepositoryImpl
import com.ericampire.android.androidstudycase.data.repository.BlogRepositoryImpl
import com.ericampire.android.androidstudycase.data.repository.LottieFileRepositoryImpl
import com.ericampire.android.androidstudycase.data.repository.UserRepositoryImpl
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun provideAnimatorRepository(
    localDataSource: LocalAnimatorDataSource,
    remoteDataSource: RemoteAnimatorDataSource,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    coroutineScope: CoroutineScope
  ): AnimatorRepository {
    return AnimatorRepositoryImpl(
      localDataSource = localDataSource,
      remoteDataSource = remoteDataSource,
      coroutineDispatcher = coroutineDispatcher,
      coroutineScope = coroutineScope
    )
  }

  @Provides
  fun provideUserRepository(
    localDataSource: LocalUserDataSource,
  ): UserRepository {
    return UserRepositoryImpl(
      localDataSource = localDataSource,
    )
  }

  @Provides
  fun provideLottieFileRepository(
    localDataSource: LocalLottieFileDataSource,
    remoteDataSource: RemoteLottieFileDataSource,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    coroutineScope: CoroutineScope
  ): LottieFileRepository {
    return LottieFileRepositoryImpl(
      localDataSource = localDataSource,
      remoteDataSource = remoteDataSource,
      coroutineDispatcher = coroutineDispatcher,
      coroutineScope = coroutineScope
    )
  }

  @Provides
  fun provideBlogRepository(
    localDataSource: LocalBlogDataSource,
    remoteDataSource: RemoteBlogDataSource,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    coroutineScope: CoroutineScope
  ) : BlogRepository {
    return BlogRepositoryImpl(
      localDataSource = localDataSource,
      remoteDataSource = remoteDataSource,
      coroutineDispatcher = coroutineDispatcher,
      coroutineScope = coroutineScope
    )
  }
}