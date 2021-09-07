package com.ericampire.android.androidstudycase.util.mvi

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : MavericksState, A>(
  initialState: S
) : MavericksViewModel<S>(initialState) {
  protected val pendingAction = MutableSharedFlow<A>()

  fun submitAction(action: A) {
    viewModelScope.launch {
      pendingAction.emit(action)
    }
  }
}