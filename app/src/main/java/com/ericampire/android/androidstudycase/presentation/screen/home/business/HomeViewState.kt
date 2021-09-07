package com.ericampire.android.androidstudycase.presentation.screen.home.business

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.entity.User

data class HomeViewState(
  val contentData: Async<HomeContentData> = Uninitialized
) : MavericksState

data class HomeContentData(
  val blog: List<Blog> = emptyList(),
  val featuredAnimators: List<Animator> = emptyList(),
  val featuredLottieFile: List<Lottiefile> = emptyList(),
  val user: User? = null,
) : MavericksState
