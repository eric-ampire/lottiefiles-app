package com.ericampire.android.androidstudycase.presentation.screen.home.business

import com.airbnb.mvrx.MavericksViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.AssistedViewModelFactory
import com.ericampire.android.androidstudycase.app.hilt.hiltMavericksViewModelFactory
import com.ericampire.android.androidstudycase.domain.usecase.*
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.mvi.BViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
  @Assisted initialState: HomeViewState,
  private val findFeaturedBlogUseCase: FindFeaturedBlogUseCase,
  private val findFeaturedAnimatorUseCase: FindFeaturedAnimatorUseCase,
  private val findUsersUseCase: FindUsersUseCase,
  private val findFeaturedLottieFileUseCase: FindFeaturedLottieFileUseCase,
  private val saveUserUseCase: SaveUserUseCase
) : BViewModel<HomeViewState, HomeAction>(initialState) {


  init {
    viewModelScope.launch {
      pendingAction.collectLatest { action ->
        when (action) {
          HomeAction.FetchData -> fetchData()
          HomeAction.Login -> login()
          HomeAction.FetchCurrentUser -> fetchCurrentUser()
        }
      }
    }
  }

  private fun login() {
    viewModelScope.launch {
      suspend {
        delay(2000)
        saveUserUseCase(PreviewData.User.data.first()).data!!
      }.execute {
        copy(login = it)
      }
    }
  }

  private fun fetchCurrentUser() {
    viewModelScope.launch {
      findUsersUseCase(Unit).map {
        it.data?.firstOrNull()
      }.execute {
        copy(currentUser = it)
      }
    }
  }

  private fun fetchData() {
    val storiesFlow = findFeaturedBlogUseCase(Unit)
    val animatorsFlow = findFeaturedAnimatorUseCase(Unit)
    val animationsFlow = findFeaturedLottieFileUseCase(Unit)

    combine(
      storiesFlow,
      animationsFlow,
      animatorsFlow
    ) { stories, anim, animators ->
      HomeContentData(
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