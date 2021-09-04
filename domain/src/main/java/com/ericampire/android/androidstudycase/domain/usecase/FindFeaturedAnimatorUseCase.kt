package com.ericampire.android.androidstudycase.domain.usecase

import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindFeaturedAnimatorUseCase @Inject constructor(
  private val repository: AnimatorRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Animator>>(dispatcher) {
  override fun execute(parameters: Unit): Flow<Result<List<Animator>>> {
    return repository.findAll()
  }
}