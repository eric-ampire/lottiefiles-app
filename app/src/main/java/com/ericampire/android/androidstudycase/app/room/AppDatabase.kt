package com.ericampire.android.androidstudycase.app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.util.room.DateConverter

@Database(
  entities = [Blog::class, Animator::class, Lottiefile::class],
  version = 2,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract val blogDao: BlogDao
  abstract val animatorDao: AnimatorDao
  abstract val lottieFileDao: LottieFilesDao
}