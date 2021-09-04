package com.ericampire.android.androidstudycase.presentation.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.*

@Composable
fun LottiePreviewDialog(
  modifier: Modifier = Modifier,
  url: String,
  onDismissRequest: () -> Unit,
  onError: (String?) -> Unit
) {

  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.Url(url),
    onRetry = { _, previousException ->
      onError(previousException.localizedMessage)
      false
    }
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  Dialog(
    onDismissRequest = onDismissRequest,
    content = {
      Box(
        modifier = modifier
          .size(400.dp)
          .background(color = Color.White, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center,
        content = {
          LottieAnimation(
            modifier = Modifier
              .matchParentSize()
              .padding(10.dp)
              .clip(MaterialTheme.shapes.medium),
            composition = composition,
            progress = progress,
          )
        }
      )
    }
  )
}