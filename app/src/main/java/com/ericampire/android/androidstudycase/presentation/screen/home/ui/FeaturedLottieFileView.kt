package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.ericampire.android.androidstudycase.util.LottieFileProvider

@ExperimentalMaterialApi
@Composable
fun FeaturedLottieFileView(
  modifier: Modifier = Modifier,
  lottiefile: Lottiefile,
  onClick: (Lottiefile) -> Unit
) {
  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.Url(lottiefile.lottieUrl)
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  Card(
    modifier = modifier,
    elevation = 0.dp,
    onClick = { onClick(lottiefile) },
    content = {
      Column(
        content = {
          Box(
            modifier = modifier
              .background(Color.White)
              .height(170.dp)
              .width(170.dp),
            content = {
              LottieAnimation(
                modifier = Modifier.matchParentSize(),
                composition = composition,
                progress = progress,
              )
            }
          )

          Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
              .background(AppColor.Black001)
              .width(170.dp)
              .padding(horizontal = 10.dp, vertical = 8.dp),
            content = {
              Text(
                maxLines = 1,
                text = lottiefile.name,
                style = MaterialTheme.typography.h6,
              )
              Text(
                text = lottiefile.createdBy?.name ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.caption.copy(
                  color = Color.Gray
                ),
              )
            }
          )
        }
      )
    }
  )
}

@ExperimentalMaterialApi
@Preview()
@Composable
fun FeaturedLottieFileViewPreview(@PreviewParameter(LottieFileProvider::class) data: Lottiefile) {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        FeaturedLottieFileView(
          lottiefile = data,
          onClick = {}
        )
      }
    )
  }
}