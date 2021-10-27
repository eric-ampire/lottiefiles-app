package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor

@Composable
fun UnLoggedUserHeaderView(
  modifier: Modifier = Modifier,
  onLoginClick: () -> Unit
) {
  Row(
    modifier = modifier
      .background(color = AppColor.Black001)
      .padding(16.dp)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    content = {
      Image(
        modifier = Modifier
          .size(57.dp)
          .clip(CircleShape),
        painter = painterResource(id = R.drawable.lottiefiles_circle_logo),
        contentDescription = stringResource(id = org.zxconnect.android.beserve.i18n.R.string.txt_lottie_logo)
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
          Text(
            maxLines = 1,
            text = "Monday 4 September",
            style = MaterialTheme.typography.button.copy(
              color = Color.Gray
            ),
          )
          Text(
            maxLines = 1,
            text = stringResource(org.zxconnect.android.beserve.i18n.R.string.txt_hello_stranger),
            style = MaterialTheme.typography.h3.copy(
              color = AppColor.PaleBlue
            ),
          )
          Text(
            modifier = Modifier.clickable(onClick = onLoginClick),
            maxLines = 1,
            text = stringResource(org.zxconnect.android.beserve.i18n.R.string.txt_login),
            style = MaterialTheme.typography.body1.copy(
              color = AppColor.Teal
            ),
          )
        }
      )
    }
  )
}

@Preview
@Composable
private fun UnLoggedUserHeaderViewPreview() {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        UnLoggedUserHeaderView() {

        }
      }
    )
  }
}