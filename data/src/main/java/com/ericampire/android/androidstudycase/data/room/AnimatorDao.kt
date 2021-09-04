package com.ericampire.android.androidstudycase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericampire.android.androidstudycase.domain.entity.Animator

@Dao
interface AnimatorDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(animator: Animator)

  @Query("SELECT * FROM Animator")
  suspend fun findAll(): List<Animator>
}