package com.ericampire.android.androidstudycase.domain.usecase

import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.repository.LottieFileRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindRecentLottieFileUseCase @Inject constructor(
  private val repository: LottieFileRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Lottiefile>>(dispatcher) {
  override fun execute(parameters: Unit): Flow<Result<List<Lottiefile>>> {
    return repository.findRecent()
  }
}

class FindPopularLottieFileUseCase @Inject constructor(
  private val repository: LottieFileRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Lottiefile>>(dispatcher) {
  override fun execute(parameters: Unit): Flow<Result<List<Lottiefile>>> {
    return repository.findPopular()
  }
}

class FindFeaturedLottieFileUseCase @Inject constructor(
  private val repository: LottieFileRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Lottiefile>>(dispatcher) {
  override fun execute(parameters: Unit): Flow<Result<List<Lottiefile>>> {
    return repository.findFeatured()
  }
}