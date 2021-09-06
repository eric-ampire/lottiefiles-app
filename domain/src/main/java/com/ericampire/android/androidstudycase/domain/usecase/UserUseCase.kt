package com.ericampire.android.androidstudycase.domain.usecase

import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.domain.repository.UserRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.usecase.CoroutineUseCase
import com.ericampire.android.androidstudycase.util.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
  private val repository: UserRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<User, Unit>(dispatcher) {
  override suspend fun execute(parameters: User) {
    return repository.save(parameters)
  }
}

class FindUsersUseCase @Inject constructor(
  private val repository: UserRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<User>>(dispatcher) {
  override fun execute(parameters: Unit): Flow<Result<List<User>>> {
    return repository.findAll()
  }
}