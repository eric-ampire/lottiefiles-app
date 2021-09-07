package com.ericampire.android.androidstudycase.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ericampire.android.androidstudycase.presentation.screen.explore.ui.ExploreScreen
import com.ericampire.android.androidstudycase.presentation.screen.home.ui.HomeScreen
import com.ericampire.android.androidstudycase.presentation.screen.preview.ui.PreviewScreen
import com.ericampire.android.androidstudycase.util.Destination
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.addHomeScreen(navController: NavController) {
  composable(Destination.Home.route) {
    HomeScreen(navController = navController)
  }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
fun NavGraphBuilder.addExploreScreen(navController: NavController) {
  composable(Destination.Explore.route) {
    ExploreScreen(navController = navController)
  }
}

@ExperimentalPermissionsApi
fun NavGraphBuilder.addPreviewScreen(navController: NavController) {
  composable(Destination.Preview.route) {
    PreviewScreen(navController = navController)
  }
}