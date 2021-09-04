package com.ericampire.android.androidstudycase.presentation.screen.home.business

import androidx.lifecycle.viewModelScope
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedAnimatorUseCase
import com.ericampire.android.androidstudycase.domain.usecase.FindFeaturedBlogUseCase
import com.ericampire.android.androidstudycase.util.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val findFeaturedBlogUseCase: FindFeaturedBlogUseCase,
  private val findFeaturedAnimatorUseCase: FindFeaturedAnimatorUseCase
) : BaseViewModel<HomeViewState, HomeEffect, HomeAction>() {

  override val container: Container<HomeViewState, HomeEffect>
    get() = container(initialState = HomeViewState())

  init {
    viewModelScope.launch {
      pendingAction.collectLatest { action ->

      }
    }
  }
}