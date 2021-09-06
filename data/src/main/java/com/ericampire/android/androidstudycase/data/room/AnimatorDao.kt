package com.ericampire.android.androidstudycase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericampire.android.androidstudycase.domain.entity.Animator
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimatorDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(animator: Animator)

  @Query("SELECT * FROM Animator")
  fun findAll(): Flow<List<Animator>>
}