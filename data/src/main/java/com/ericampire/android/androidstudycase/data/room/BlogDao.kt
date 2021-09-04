package com.ericampire.android.androidstudycase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericampire.android.androidstudycase.domain.entity.Blog

@Dao
interface BlogDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(blog: Blog)

  @Query("SELECT * FROM Blog")
  suspend fun findAll(): List<Blog>
}