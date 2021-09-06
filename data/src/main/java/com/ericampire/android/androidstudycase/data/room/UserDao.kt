package com.ericampire.android.androidstudycase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ericampire.android.androidstudycase.domain.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Insert
  fun save(user: User)

  @Query("SELECT * FROM User")
  fun findAll(): Flow<List<User>>
}