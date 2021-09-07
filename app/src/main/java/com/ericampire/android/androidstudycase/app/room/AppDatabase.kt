package com.ericampire.android.androidstudycase.app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.data.room.LottieFilesDao
import com.ericampire.android.androidstudycase.data.room.UserDao
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.entity.User

@Database(
  entities = [Blog::class, Animator::class, Lottiefile::class, User::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract val blogDao: BlogDao
  abstract val userDao: UserDao
  abstract val animatorDao: AnimatorDao
  abstract val lottieFileDao: LottieFilesDao
}