package com.ericampire.android.androidstudycase.presentation.screen.explore.business

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile

data class ExploreViewState(
  val files: Async<List<Lottiefile>> = Uninitialized,
) : MavericksState
