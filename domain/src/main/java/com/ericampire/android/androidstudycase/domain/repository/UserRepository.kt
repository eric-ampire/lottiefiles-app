package com.ericampire.android.androidstudycase.domain.repository

import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
  suspend fun save(user: User)
  fun findAll(): Flow<Result<List<User>>>
}