package com.ericampire.android.androidstudycase.presentation.screen.explore.business

import androidx.lifecycle.viewModelScope
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedLottieFileUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindPopularLottieFileUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindRecentLottieFileUseCase
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel  @Inject constructor(
  private val findPopularLottieFileUseCase: FindPopularLottieFileUseCase,
  private val findRecentLottieFileUseCase: FindRecentLottieFileUseCase,
  private val findFeaturedLottieFileUseCase: FindFeaturedLottieFileUseCase
) : BaseViewModel<ExploreViewState, ExploreEffect, ExploreAction>() {

  override val container: Container<ExploreViewState, ExploreEffect>
    get() = container(initialState = ExploreViewState())

  init {
    viewModelScope.launch {
      pendingAction.collectLatest { action ->
        when (action) {
          ExploreAction.FindFeaturedFile -> findFeaturedLottieFileUseCase(Unit).fetchData()
          ExploreAction.FindPopularFile -> findPopularLottieFileUseCase(Unit).fetchData()
          ExploreAction.FindRecentFile -> findRecentLottieFileUseCase(Unit).fetchData()
        }
      }
    }
  }

  private fun Flow<Result<List<Lottiefile>>>.fetchData() = intent(registerIdling = false) {
    collect { result ->
      when (result) {
        is Result.Error -> {
          val errorMessage = result.exception.localizedMessage ?: "Unknown Error"
          reduce { state.copy(isLoading = false) }
          postSideEffect(ExploreEffect.ShowErrorMessage(errorMessage))
        }
        Result.Loading -> reduce {
          state.copy(isLoading = true)
        }
        is Result.Success -> reduce {
          state.copy(files = result.data, isLoading = false)
        }
      }
    }
  }
}