package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor

@Composable
fun BrowseAllItemView(modifier: Modifier = Modifier) {

  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.Url("https://assets8.lottiefiles.com/packages/lf20_iu2eauds.json")
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  Row(
    modifier = modifier
      .background(AppColor.Black001)
      .padding(18.dp)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    content = {
      Column(
        modifier = Modifier.height(130.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        content = {
          Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(end = 12.dp)
          ) {
            Text(
              text = stringResource(org.zxconnect.android.beserve.i18n.R.string.txt_browse_all),
              style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.onSurface
              ),
            )

            Text(
              text = stringResource(org.zxconnect.android.beserve.i18n.R.string.txt_browse_all_desc),
              style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface
              ),
            )
          }

          TextButton(onClick = { /*TODO*/ }) {
            Text(
              text = stringResource(org.zxconnect.android.beserve.i18n.R.string.txt_go_to_explore),
            )
          }
        }
      )

      Box(
        modifier = Modifier
          .size(130.dp)
          .clip(MaterialTheme.shapes.medium)
          .background(Color.White),
        content = {
          LottieAnimation(
            modifier = Modifier.matchParentSize(),
            composition = composition,
            progress = progress,
          )
        }
      )
    }
  )
}


@ExperimentalMaterialApi
@Preview()
@Composable
fun BrowseAllItemViewPreview() {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        BrowseAllItemView()
      }
    )
  }
}