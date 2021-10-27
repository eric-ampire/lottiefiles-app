package com.ericampire.android.androidstudycase.util.mvi.core



interface Reducer<S: State, A: Action> {

  /**
   * Given a [currentState] and some [action] that user took, produce a new [State].
   *
   * This will give us and predictable state management, that ensures each state is associated
   * with some specific user intent or action
   */
  fun reduce(currentState: S, action: A): S
}