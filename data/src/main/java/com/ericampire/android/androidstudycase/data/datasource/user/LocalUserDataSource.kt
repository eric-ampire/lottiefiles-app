package com.ericampire.android.androidstudycase.data.datasource.user

import com.ericampire.android.androidstudycase.data.room.UserDao
import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataSource @Inject constructor(
  private val dao: UserDao
) : UserDataSource {
  override suspend fun save(user: User) {
    dao.save(user)
  }

  override fun findAll(): Flow<Result<List<User>>> {
    return dao.findAll().map {
      Result.Success(it)
    }
  }
}