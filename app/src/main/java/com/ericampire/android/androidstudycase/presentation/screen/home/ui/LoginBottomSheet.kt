package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.custom.CustomFacebookButton
import com.ericampire.android.androidstudycase.presentation.custom.CustomGoogleButton
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor

@ExperimentalAnimationApi
@Composable
fun LoginBottomSheet(
  modifier: Modifier = Modifier,
  isLoading: Boolean = false,
  onLoginClick: () -> Unit,
) {

  val progressBarAlpha = if (isLoading) 1f else 0f

  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.RawRes(R.raw.people_communicating)
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  Column(
    modifier = modifier
      .clip(MaterialTheme.shapes.medium)
      .background(AppColor.Black001),
    content = {
      LinearProgressIndicator(
        modifier = Modifier
          .fillMaxWidth()
          .alpha(progressBarAlpha)
      )

      Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
          Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.txt_login_title),
            style = MaterialTheme.typography.h4.copy(
              color = MaterialTheme.colors.onSurface
            ),
          )
          Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.txt_login_description),
            style = MaterialTheme.typography.body1.copy(
              color = MaterialTheme.colors.onSurface
            ),
          )
          LottieAnimation(
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp),
            composition = composition,
            progress = progress,
          )
          Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = org.zxconnect.android.beserve.i18n.R.string.txt_get_started),
            style = MaterialTheme.typography.body1.copy(
              color = MaterialTheme.colors.onSurface
            ),
          )

          CustomFacebookButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginClick
          )

          CustomGoogleButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginClick
          )
        }
      )
    }
  )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun LoginDialogViewPreview() {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        LoginBottomSheet(
          onLoginClick = {}
        )
      }
    )
  }
}