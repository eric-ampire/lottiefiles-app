package com.ericampire.android.androidstudycase.data.datasource.user

import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
  suspend fun save(user: User)
  fun findAll(): Flow<Result<List<User>>>
}