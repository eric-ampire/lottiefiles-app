package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.user.UserDataSource
import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
  private val localDataSource: UserDataSource
) : UserRepository {
  override suspend fun save(user: User) {
    return localDataSource.save(user)
  }

  override fun findAll(): Flow<Result<List<User>>> {
    return localDataSource.findAll()
  }
}