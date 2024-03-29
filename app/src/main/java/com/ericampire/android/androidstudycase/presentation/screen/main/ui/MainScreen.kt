package com.ericampire.android.androidstudycase.presentation.screen.main.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Escalator
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ericampire.android.androidstudycase.app.addExploreScreen
import com.ericampire.android.androidstudycase.app.addHomeScreen
import com.ericampire.android.androidstudycase.app.addPreviewScreen
import com.ericampire.android.androidstudycase.util.Destination
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@Composable
fun MainScreen() {
  val navController = rememberNavController()

  val items = listOf(
    Destination.Home,
    Destination.Preview,
    Destination.Explore,
  )

  Scaffold(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    bottomBar = {
      BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        content = {
          val navBackStackEntry by navController.currentBackStackEntryAsState()
          val currentDestination = navBackStackEntry?.destination
          items.forEachIndexed { index, screen ->
            BottomNavigationItem(
              selectedContentColor = MaterialTheme.colors.primary,
              unselectedContentColor = Color.Gray,
              icon = {
                Icon(
                  imageVector = getIconByIndex(index),
                  contentDescription = null
                )
              },
              label = {
                Text(text = stringResource(screen.resourceId))
              },
              selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
              onClick = {
                navController.navigate(screen.route) {
                  popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                  }
                  launchSingleTop = true
                  restoreState = true
                }
              }
            )
          }
        }
      )
    },
    content = { innerPadding ->
      NavHost(
        navController = navController,
        modifier = Modifier.padding(innerPadding),
        startDestination = Destination.Home.route,
        builder = {
          addHomeScreen(navController = navController)
          addPreviewScreen(navController = navController)
          addExploreScreen(navController = navController)
        }
      )
    },
  )
}

fun getIconByIndex(index: Int): ImageVector {
  return when (index) {
    0 -> Icons.Rounded.Home
    1 -> Icons.Rounded.QrCodeScanner
    2 -> Icons.Rounded.Escalator
    else -> Icons.Rounded.Home
  }
}