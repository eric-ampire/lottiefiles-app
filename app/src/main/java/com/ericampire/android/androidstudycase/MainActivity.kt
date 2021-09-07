package com.ericampire.android.androidstudycase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.ericampire.android.androidstudycase.presentation.screen.main.ui.MainScreen
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @ExperimentalMaterialApi
  @ExperimentalPagerApi
  @ExperimentalPermissionsApi

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AndroidStudyCaseTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
          MainScreen()
        }
      }
    }
  }
}