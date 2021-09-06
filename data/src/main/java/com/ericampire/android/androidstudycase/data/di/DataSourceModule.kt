package com.ericampire.android.androidstudycase.data.di

import com.ericampire.android.androidstudycase.data.datasource.animator.AnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.animator.LocalAnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.animator.RemoteAnimatorDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.BlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.LocalBlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.blog.RemoteBlogDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LocalLottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.RemoteLottieFileDataSource
import com.ericampire.android.androidstudycase.data.datasource.user.LocalUserDataSource
import com.ericampire.android.androidstudycase.data.datasource.user.UserDataSource
import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.data.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json as KotlinJson


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

  @Provides
  fun provideHttpClient(): HttpClient {
    return HttpClient(Android) {
      install(JsonFeature) {
        val jsonSetup = KotlinJson {
          isLenient = true
          ignoreUnknownKeys = true
          prettyPrint = true
        }
        serializer = KotlinxSerializer(jsonSetup)
      }
    }
  }

  @Provides
  fun provideLocalAnimatorDataSource(
    animatorDao: AnimatorDao
  ): AnimatorDataSource {
    return LocalAnimatorDataSource(animatorDao)
  }

  @Provides
  fun provideRemoteBlogDataSource(
    httpClient: HttpClient
  ): BlogDataSource {
    return RemoteBlogDataSource(httpClient)
  }

  @Provides
  fun provideLocalBlogDataSource(
    dao: BlogDao
  ): BlogDataSource {
    return LocalBlogDataSource(dao)
  }

  @Provides
  fun provideLocalUserDataSource(
    dao: UserDao
  ): UserDataSource {
    return LocalUserDataSource(dao)
  }

  @Provides
  fun provideRemoteAnimatorDataSource(
    httpClient: HttpClient
  ): AnimatorDataSource {
    return RemoteAnimatorDataSource(httpClient)
  }

  @Provides
  fun provideRemoteLottieFileDataSource(
    httpClient: HttpClient
  ): LottieFileDataSource {
    return RemoteLottieFileDataSource(httpClient)
  }

  @Provides
  fun provideLocalLottieFileDataSource(
    dao: LottieFilesDao
  ): LottieFileDataSource {
    return LocalLottieFileDataSource(dao)
  }
}