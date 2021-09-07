package com.ericampire.android.androidstudycase.presentation.screen.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.CreateNewFolder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.presentation.custom.CustomImageView
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.ericampire.android.androidstudycase.util.LottieFileProvider

@ExperimentalMaterialApi
@Composable
fun LottieFileItemView(
  modifier: Modifier = Modifier,
  lottiefile: Lottiefile,
  onClick: (Lottiefile) -> Unit,
  onShareClick: (Lottiefile) -> Unit,
) {
  Card(
    modifier = modifier,
    elevation = 0.dp,
    onClick = { onClick(lottiefile) },
    shape = MaterialTheme.shapes.large.copy(all = CornerSize(0.dp)),
    content = {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
          Row(
            modifier = Modifier
              .background(AppColor.Black001)
              .padding(12.dp)
              .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
              Box(
                modifier = Modifier
                  .size(44.dp)
                  .clip(CircleShape),
                content = {
                  CustomImageView(
                    modifier = Modifier.matchParentSize(),
                    data = lottiefile.createdBy?.avatarUrl ?: "null",
                  )
                  Box(
                    modifier = Modifier
                      .matchParentSize()
                      .background(color = AppColor.BlackOverlay001)
                  )
                }
              )

              Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                  Text(
                    text = lottiefile.name,
                    style = MaterialTheme.typography.h6,
                  )
                  Text(
                    text = lottiefile.createdBy?.name ?: "",
                    style = MaterialTheme.typography.caption,
                  )
                }
              )
            }
          )
          LottieFileContentView(lottiefile = lottiefile)

          ActionButton(
            onLikeClick = { /*TODO*/ },
            onCommentClick = { /*TODO*/ },
            onAddCollectionClick = { /*TODO*/ },
            onShareClick = {
              onShareClick(lottiefile)
            }
          )
        }
      )
    }
  )
}


@Composable
fun LottieFileContentView(
  modifier: Modifier = Modifier,
  lottiefile: Lottiefile
) {

  val composition by rememberLottieComposition(
    spec = LottieCompositionSpec.Url(lottiefile.lottieUrl)
  )
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
  )

  Box(
    modifier = modifier.background(Color.White),
    content = {
      LottieAnimation(
        modifier = Modifier
          .fillMaxWidth()
          .height(400.dp),
        composition = composition,
        progress = progress,
      )
    }
  )
}

@Composable
private fun ActionButton(
  modifier: Modifier = Modifier,
  onLikeClick: () -> Unit,
  onCommentClick: () -> Unit,
  onAddCollectionClick: () -> Unit,
  onShareClick: () -> Unit,
) {
  Row(
    modifier = modifier
      .background(AppColor.Black001)
      .padding(12.dp)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    content = {
      Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = {
          IconButton(
            modifier = Modifier.size(20.dp),
            onClick = onLikeClick,
            content = {
              Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null
              )
            }
          )
          IconButton(
            modifier = Modifier.size(20.dp),
            onClick = onCommentClick,
            content = {
              Icon(
                imageVector = Icons.Rounded.ChatBubble,
                contentDescription = null
              )
            }
          )
          IconButton(
            modifier = Modifier.size(20.dp),
            onClick = onAddCollectionClick,
            content = {
              Icon(
                imageVector = Icons.Rounded.CreateNewFolder,
                contentDescription = null
              )
            }
          )
        }
      )
      IconButton(
        modifier = Modifier.size(20.dp),
        onClick = onShareClick,
        content = {
          Icon(
            imageVector = Icons.Rounded.Share,
            contentDescription = null
          )
        }
      )
    }
  )
}

@ExperimentalMaterialApi
@Preview()
@Composable
fun LottiefileItemViewPreview(@PreviewParameter(LottieFileProvider::class) data: Lottiefile) {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        LottieFileItemView(
          lottiefile = data,
          onClick = {},
          onShareClick = {},
        )
      }
    )
  }
}