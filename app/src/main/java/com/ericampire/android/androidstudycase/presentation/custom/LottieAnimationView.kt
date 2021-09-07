package com.ericampire.android.androidstudycase.presentation.custom

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun LottieAnimationView(
  modifier: Modifier = Modifier,
  resId: Int
) {
  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.RawRes(resId)
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  LottieAnimation(
    modifier = modifier.padding(10.dp),
    composition = composition,
    progress = progress,
  )
}