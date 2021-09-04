package com.ericampire.android.androidstudycase.presentation.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.R

@Composable
fun TopActionBar(modifier: Modifier = Modifier) {
  TopAppBar(
    elevation = 0.dp,
    modifier = modifier,
    backgroundColor = Color.Transparent,
    title = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = {
          Image(
            modifier = Modifier.height(23.dp),
            painter = painterResource(id = R.drawable.ic_lottiefiles_logo),
            contentDescription = stringResource(id = R.string.txt_lottie_logo)
          )
        }
      )
    },
  )
}