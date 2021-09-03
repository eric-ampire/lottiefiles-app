package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.data.datasource.user.UserDataSource
import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
  private val dataSource: UserDataSource
) : UserRepository {
}