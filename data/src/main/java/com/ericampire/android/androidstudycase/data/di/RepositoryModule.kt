package com.ericampire.android.androidstudycase.data.di

import com.ericampire.android.androidstudycase.data.datasource.animator.AnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.animator.LocalAnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.animator.RemoteAnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.LocalBlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.RemoteBlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LocalLottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.RemoteLottieFileDataSource
import com.ericampire.android.androidstudycase.data.repository.AnimatorRepositoryImpl
import com.ericampire.android.androidstudycase.data.repository.BlogRepositoryImpl
import com.ericampire.android.androidstudycase.data.repository.LottieFileRepositoryImpl
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun provideAnimatorRepository(
    localDataSource: LocalAnimatorDataSource,
    remoteDataSource: RemoteAnimatorDataSource,
  ) : AnimatorRepository {
    return AnimatorRepositoryImpl(
      localDataSource = localDataSource,
      remoteDataSource = remoteDataSource
    )
  }

  @Provides
  fun provideLottieFileRepository(
    localDataSource: LocalLottieFileDataSource,
    remoteDataSource: RemoteLottieFileDataSource,
  ) : LottieFileRepository {
    return LottieFileRepositoryImpl(
      localDataSource = localDataSource,
      remoteDataSource = remoteDataSource
    )
  }

  @Provides
  fun provideBlogRepository(
    localDataSource: LocalBlogDataSource,
    remoteDataSource: RemoteBlogDataSource,
  ) : BlogRepository {
    return BlogRepositoryImpl(
      localDataSource = localDataSource,
      remoteDataSource = remoteDataSource
    )
  }
}