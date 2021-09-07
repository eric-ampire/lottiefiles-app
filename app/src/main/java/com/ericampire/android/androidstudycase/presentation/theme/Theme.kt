package com.ericampire.android.androidstudycase.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets

private val DarkColorPalette = darkColors(
  primary = AppColor.PrimaryColor,
  primaryVariant = AppColor.PrimaryColor,
  secondary = AppColor.Teal200,
  background = Color.Black,
  surface = Color.Black,
  onPrimary = Color.Black,
  onSecondary = Color.White,
  onBackground = Color.White,
  onSurface = Color.White,
)

private val LightColorPalette = lightColors(
  primary = AppColor.PrimaryColorDark,
  primaryVariant = AppColor.PrimaryColor,
  secondary = AppColor.Teal200,
  background = Color.White,
  surface = Color.White,
  onPrimary = Color.White,
  onSecondary = Color.Black,
  onBackground = Color.Black,
  onSurface = Color.Black,
)

@Composable
fun AndroidStudyCaseTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = {
      ProvideWindowInsets {
        content()
      }
    }
  )
}