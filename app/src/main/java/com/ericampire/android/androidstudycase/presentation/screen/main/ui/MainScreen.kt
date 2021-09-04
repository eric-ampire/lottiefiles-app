package com.ericampire.android.androidstudycase.presentation.screen.main.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Scanner
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ericampire.android.androidstudycase.app.addExploreScreen
import com.ericampire.android.androidstudycase.app.addHomeScreen
import com.ericampire.android.androidstudycase.app.addPreviewScreen
import com.ericampire.android.androidstudycase.util.Destination

@Composable
fun MainScreen() {
  val navController = rememberNavController()

  val items = listOf(
    Destination.Home,
    Destination.Preview,
    Destination.Explore,
  )

  Scaffold(
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
    1 -> Icons.Rounded.Scanner
    2 -> Icons.Rounded.Explore
    else -> Icons.Rounded.Home
  }
}