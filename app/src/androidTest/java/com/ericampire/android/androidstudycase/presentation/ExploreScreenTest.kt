package com.ericampire.android.androidstudycase.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.ericampire.android.androidstudycase.MainActivity
import com.ericampire.android.androidstudycase.presentation.screen.explore.ui.ExploreScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
@ExperimentalPagerApi
class ExploreScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  @Ignore
  fun whenSubmitActionShouldReturnData() {

    composeTestRule.setContent {
      ExploreScreen(navController = rememberNavController())
    }

    composeTestRule.onNodeWithText("Recent").performClick().assertIsSelected()
    composeTestRule.onNodeWithText("Featured").performClick().assertIsSelected()
    composeTestRule.onNodeWithText("Popular").performClick().assertIsSelected()
  }
}