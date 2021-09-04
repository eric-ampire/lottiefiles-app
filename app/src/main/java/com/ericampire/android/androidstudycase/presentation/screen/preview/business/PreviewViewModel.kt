package com.ericampire.android.androidstudycase.presentation.screen.preview.business

import androidx.lifecycle.viewModelScope
import com.ericampire.android.androidstudycase.util.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(

) : BaseViewModel<PreviewViewState, PreviewEffect, PreviewAction>() {

  override val container: Container<PreviewViewState, PreviewEffect>
    get() = container(initialState = PreviewViewState())

  init {
    viewModelScope.launch {
      pendingAction.collectLatest { action ->

      }
    }
  }
}