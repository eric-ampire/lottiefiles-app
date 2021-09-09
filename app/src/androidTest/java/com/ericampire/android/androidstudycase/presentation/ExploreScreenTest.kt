package com.ericampire.android.androidstudycase.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.ericampire.android.androidstudycase.MainActivity
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreAction
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewModel
import com.ericampire.android.androidstudycase.presentation.screen.explore.ui.ExploreScreen
import com.ericampire.android.androidstudycase.util.PreviewData
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalMaterialApi
@ExperimentalPagerApi
class ExploreScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun whenSubmitActionShouldReturnData() {
    val exploreViewModel = mock<ExploreViewModel> {
      on { submitAction(ExploreAction.FindPopularFile) }
      doReturn(PreviewData.Lottiefile.data)
    }
    composeTestRule.setContent {
      ExploreScreen(
        navController = rememberNavController(),
        viewModel = exploreViewModel
      )
    }
    verify(exploreViewModel).submitAction(any())
  }
}