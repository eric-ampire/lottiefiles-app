package com.ericampire.android.androidstudycase.presentation.screen.home.business

import com.airbnb.mvrx.MavericksViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.AssistedViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.hiltMavericksViewModelFactory
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedAnimatorUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedBlogUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedLottieFileUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindUsersUseCase
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.mvi.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
  @Assisted initialState: HomeViewState,
  private val findFeaturedBlogUseCase: FindFeaturedBlogUseCase,
  private val findFeaturedAnimatorUseCase: FindFeaturedAnimatorUseCase,
  private val findUsersUseCase: FindUsersUseCase,
  private val findFeaturedLottieFileUseCase: FindFeaturedLottieFileUseCase
) : BaseViewModel<HomeViewState, HomeAction>(initialState) {


  init {
    viewModelScope.launch {
      pendingAction.collectLatest { action ->
        when (action) {
          HomeAction.FetchData -> fetchData()
        }
      }
    }
  }

  private fun fetchData() {
    val userFlow = findUsersUseCase(Unit)
    val storiesFlow = findFeaturedBlogUseCase(Unit)
    val animatorsFlow = findFeaturedAnimatorUseCase(Unit)
    val animationsFlow = findFeaturedLottieFileUseCase(Unit)

    combine(
      userFlow,
      storiesFlow,
      animationsFlow,
      animatorsFlow
    ) { user, stories, anim, animators ->
      HomeContentData(
        user = user.data?.firstOrNull(),
        blog = stories.data ?: emptyList(),
        featuredAnimators = animators.data ?: emptyList(),
        featuredLottieFile = anim.data ?: emptyList(),
      )
    }.execute {
      copy(contentData = it)
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<HomeViewModel, HomeViewState> {
    override fun create(state: HomeViewState): HomeViewModel
  }

  companion object : MavericksViewModelFactory<HomeViewModel, HomeViewState>
  by hiltMavericksViewModelFactory()
}