package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.presentation.custom.CustomImageView
import com.ericampire.android.androidstudycase.presentation.theme.AndroidStudyCaseTheme
import com.ericampire.android.androidstudycase.util.BlogProvider
import com.ericampire.android.androidstudycase.util.LottieFileProvider

@Composable
fun BlogItemView(
  modifier: Modifier = Modifier,
  blog: Blog,
  onClick: (Blog) -> Unit
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .clickable {
        onClick(blog)
      },
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    verticalAlignment = Alignment.CenterVertically,
    content = {
      CustomImageView(
        modifier = Modifier
          .height(80.dp)
          .width(130.dp)
          .clip(MaterialTheme.shapes.medium),
        data = blog.imageUrl,
      )

      Text(
        maxLines = 3,
        text = blog.title,
        style = MaterialTheme.typography.h6,
      )
    }
  )
}

@ExperimentalMaterialApi
@Preview()
@Composable
fun BlogItemViewPreview(@PreviewParameter(BlogProvider::class) data: Blog) {
  AndroidStudyCaseTheme(darkTheme = true) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
      content = {
        BlogItemView(
          blog = data,
          onClick = {}
        )
      }
    )
  }
}