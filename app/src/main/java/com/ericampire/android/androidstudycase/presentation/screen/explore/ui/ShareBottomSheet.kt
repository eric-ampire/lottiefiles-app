package com.ericampire.android.androidstudycase.presentation.screen.explore.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.InsertDriveFile
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.ericampire.android.androidstudycase.util.LottieFileProvider

@Composable
fun ShareBottomSheet(
  modifier: Modifier = Modifier,
  isLoading: Boolean = false,
  lottiefile: Lottiefile?,
  onCopyLink: (String) -> Unit,
  onShareJsonFile: (String?) -> Unit,
  onShareGifFile: (String?) -> Unit,
  onShareVideoFile: (String?) -> Unit,
) {

  val progressBarAlpha = if (isLoading) 1f else 0f
  Column(
    modifier = modifier
      .background(AppColor.Black001),
    content = {
      LinearProgressIndicator(
        modifier = Modifier
          .fillMaxWidth()
          .alpha(progressBarAlpha)
      )
      Column(
        modifier = modifier
          .padding(16.dp)
          .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
          Text(
            text = stringResource(id = R.string.txt_share_animation),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4.copy(
              color = MaterialTheme.colors.onSurface
            ),
          )

          Column(
            modifier = Modifier
              .fillMaxWidth()
              .clip(MaterialTheme.shapes.medium)
              .background(AppColor.Arsenic)
              .clickable { onCopyLink(lottiefile?.lottieUrl ?: "") }
              .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
              Icon(
                modifier = Modifier.size(48.dp),
                tint = AppColor.SlateGray,
                imageVector = Icons.Rounded.Link,
                contentDescription = null
              )
              Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.txt_share_animation),
                style = MaterialTheme.typography.body1.copy(
                  color = MaterialTheme.colors.onSurface
                ),
              )
            }
          )

          Column(
            modifier = Modifier
              .clip(MaterialTheme.shapes.medium)
              .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
              ShareItem(
                modifier = Modifier.clickable {
                  onShareJsonFile(lottiefile?.lottieUrl)
                },
                title = "Lottie JSON",
                fileExtension = ".json",
                icon = Icons.Rounded.InsertDriveFile
              )
              Divider(modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray))
              ShareItem(
                modifier = Modifier.clickable {
                  onShareGifFile(lottiefile?.gifUrl)
                },
                title = "Animated GIF",
                fileExtension = ".gif",
                icon = Icons.Rounded.InsertDriveFile
              )
              Divider(modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray))
              ShareItem(
                modifier = Modifier.clickable {
                  onShareVideoFile(lottiefile?.videoUrl)
                },
                title = "Video MP4",
                fileExtension = ".mp4",
                icon = Icons.Rounded.InsertDriveFile
              )
            }
          )
        }
      )
    }
  )
}

@Composable
private fun ShareItem(
  modifier: Modifier = Modifier,
  title: String,
  fileExtension: String,
  icon: ImageVector
) {

  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .background(AppColor.Arsenic)
      .padding(24.dp)
      .fillMaxWidth(),
    content = {
      Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = {
          Icon(
            imageVector = icon,
            tint = AppColor.SlateGray,
            contentDescription = null
          )
          Text(
            text = title,
            style = MaterialTheme.typography.h4.copy(
              color = MaterialTheme.colors.onSurface
            ),
          )
        }
      )

      Text(
        text = fileExtension,
        style = MaterialTheme.typography.body1.copy(
          color = Color.Gray
        ),
      )
    }
  )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview()
@Composable
fun ShareBottomSheetPreview(@PreviewParameter(LottieFileProvider::class) data: Lottiefile) {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        ShareBottomSheet(
          lottiefile = data,
          isLoading = true,
          onCopyLink = {},
          onShareGifFile = {},
          onShareJsonFile = {},
          onShareVideoFile = {}
        )
      }
    )
  }
}