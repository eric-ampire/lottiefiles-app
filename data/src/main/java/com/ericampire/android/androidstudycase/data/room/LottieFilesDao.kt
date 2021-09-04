package com.ericampire.android.androidstudycase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile

@Dao
interface LottieFilesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(lottiefile: Lottiefile)

  @Query("SELECT * FROM Lottiefile")
  suspend fun findPopular(): List<Lottiefile>

  @Query("SELECT * FROM Lottiefile")
  suspend fun findFeatured(): List<Lottiefile>

  @Query("SELECT * FROM Lottiefile")
  suspend fun findRecent(): List<Lottiefile>
}