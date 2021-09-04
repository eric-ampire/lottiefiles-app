package com.ericampire.android.androidstudycase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ericampire.android.androidstudycase.presentation.screen.main.ui.MainScreen
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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