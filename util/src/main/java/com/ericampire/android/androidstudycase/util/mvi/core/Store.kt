package com.ericampire.android.androidstudycase.util.mvi.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * A [Store] is our state container for a given screen
 *
 * @param[initialState] this is the initial state of the screen when it is first creates
 * @param[reducer] A system for taking in the current state, and a new action, and outputting the
 * updated state
 */
abstract class Store<S: State, A: Action>(
  initialState: S,
  private val reducer: Reducer<S, A>
) {
  private val _state = MutableStateFlow(initialState)
  val state: StateFlow<S> = _state

  fun dispatch(action: A) {
    val currentState = _state.value
    _state.value = reducer.reduce(currentState, action)
  }
}