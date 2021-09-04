package com.ericampire.android.androidstudycase.presentation.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.RawRes(R.raw.loading)
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  LottieAnimation(
    modifier = modifier.size(100.dp),
    composition = composition,
    progress = progress,
  )
}

@ExperimentalMaterialApi
@Preview()
@Composable
fun LoadingViewPreview() {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        LoadingView()
      }
    )
  }
}