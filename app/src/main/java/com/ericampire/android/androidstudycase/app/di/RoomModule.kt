package com.ericampire.android.androidstudycase.app.di

import android.content.Context
import androidx.room.Room
import com.ericampire.android.androidstudycase.app.room.AppDatabase
import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  @Provides
  fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
    val db = Room.databaseBuilder(
      context,
      AppDatabase::class.java, "database-name"
    )
    return db.build()
  }

  @Provides
  fun provideBlogDao(appDatabase: AppDatabase): BlogDao {
    return appDatabase.blogDao
  }

  @Provides
  fun provideAnimatorDao(appDatabase: AppDatabase): AnimatorDao {
    return appDatabase.animatorDao
  }

  @Provides
  fun provideLottieFilesDao(appDatabase: AppDatabase): LottieFilesDao {
    return appDatabase.lottieFileDao
  }
}