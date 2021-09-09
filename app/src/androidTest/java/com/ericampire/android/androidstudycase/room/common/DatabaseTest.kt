package com.ericampire.android.androidstudycase.room.common

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.ericampire.android.androidstudycase.app.room.AppDatabase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


abstract class DatabaseTest {
  lateinit var db: AppDatabase

  @BeforeEach
  fun initDB() {
    db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
      .allowMainThreadQueries()
      .build()
  }

  @AfterEach
  fun closeDB() {
    db.close()
  }
}