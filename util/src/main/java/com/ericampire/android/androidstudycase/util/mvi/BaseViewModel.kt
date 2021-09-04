package com.ericampire.android.androidstudycase.util.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<S: Any, E: Any, A> : ContainerHost<S, E>, ViewModel() {
  protected val pendingAction = MutableSharedFlow<A>()

  fun submitAction(action: A) {
    viewModelScope.launch {
      pendingAction.emit(action)
    }
  }
}