package com.ericampire.android.androidstudycase.presentation.screen.explore.business

import com.airbnb.mvrx.MavericksViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.AssistedViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.hiltMavericksViewModelFactory
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedLottieFileUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindPopularLottieFileUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindRecentLottieFileUseCase
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.mvi.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ExploreViewModel @AssistedInject constructor(
  @Assisted initialState: ExploreViewState,
  private val findPopularLottieFileUseCase: FindPopularLottieFileUseCase,
  private val findRecentLottieFileUseCase: FindRecentLottieFileUseCase,
  private val findFeaturedLottieFileUseCase: FindFeaturedLottieFileUseCase
) : BaseViewModel<ExploreViewState, ExploreAction>(initialState) {

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

  private fun Flow<Result<List<Lottiefile>>>.fetchData() {
    viewModelScope.launch {
      map {
        it.data ?: emptyList()
      }.execute {
        copy(files = it)
      }
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<ExploreViewModel, ExploreViewState> {
    override fun create(state: ExploreViewState): ExploreViewModel
  }

  companion object : MavericksViewModelFactory<ExploreViewModel, ExploreViewState>
  by hiltMavericksViewModelFactory()
}