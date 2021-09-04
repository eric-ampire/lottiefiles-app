package com.ericampire.android.androidstudycase.domain.usecase

import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.repository.AnimatorRepository
import com.ericampire.android.androidstudycase.domain.repository.BlogRepository
import com.ericampire.android.androidstudycase.util.IoDispatcher
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindFeaturedBlogUseCase @Inject constructor(
  private val repository: BlogRepository,
  @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Blog>>(dispatcher) {
  override fun execute(parameters: Unit): Flow<Result<List<Blog>>> {
    return repository.findAll()
  }
}