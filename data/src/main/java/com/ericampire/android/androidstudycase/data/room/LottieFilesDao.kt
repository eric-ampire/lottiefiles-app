package com.ericampire.android.androidstudycase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import kotlinx.coroutines.flow.Flow

@Dao
interface LottieFilesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(lottiefile: Lottiefile)

  @Query("SELECT * FROM Lottiefile WHERE type = :type")
  fun findByType(type: String): Flow<List<Lottiefile>>
}