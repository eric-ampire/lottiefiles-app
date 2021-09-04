package com.ericampire.android.androidstudycase.presentation.custom

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.google.accompanist.glide.rememberGlidePainter
import com.ericampire.android.androidstudycase.R

@Composable
fun CustomImageView(
  modifier: Modifier = Modifier,
  data: String,
) {
  Image(
    modifier = modifier,
    painter = rememberGlidePainter(
      request = data,
      fadeIn = true
    ),
    contentScale = ContentScale.Crop,
    contentDescription = null,
  )
}