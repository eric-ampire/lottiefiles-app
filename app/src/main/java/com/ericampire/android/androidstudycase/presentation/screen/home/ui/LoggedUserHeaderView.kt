package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.presentation.custom.CustomImageView
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.ericampire.android.androidstudycase.util.UserProvider

@Composable
fun LoggedUserHeaderView(
  modifier: Modifier = Modifier,
  user: User
) {
  Row(
    modifier = modifier
      .background(color = AppColor.Black001)
      .padding(16.dp)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    content = {
      CustomImageView(
        modifier = Modifier
          .size(57.dp)
          .clip(CircleShape),
        data = user.imageUrl,
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
          Text(
            maxLines = 1,
            text = "Monday September 4",
            style = MaterialTheme.typography.button.copy(
              color = Color.Gray
            ),
          )
          Text(
            maxLines = 1,
            text = user.displayName,
            style = MaterialTheme.typography.h3.copy(
              color = AppColor.PaleBlue
            ),
          )
        }
      )
    }
  )
}

@Preview
@Composable
private fun LoggedUserHeaderViewPreview(@PreviewParameter(UserProvider::class) data: User) {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        LoggedUserHeaderView(
          user = data,
        )
      }
    )
  }
}