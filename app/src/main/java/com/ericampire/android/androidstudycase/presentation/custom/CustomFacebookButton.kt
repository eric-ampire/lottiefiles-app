package com.ericampire.android.androidstudycase.presentation.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.theme.AppColor

@Composable
fun CustomFacebookButton(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  onClick: () -> Unit
) {
  Button(
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(backgroundColor = AppColor.BlueFacebook),
    modifier = modifier
      .fillMaxWidth()
      .height(50.dp),
    onClick = onClick,
    content = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
          Icon(
            tint = Color.White,
            imageVector = Icons.Rounded.Facebook,
            contentDescription = null,
          )
          Text(
            color = Color.White,
            style = MaterialTheme.typography.h5,
            text = stringResource(R.string.txt_connect_with_facebook)
          )
          Icon(
            tint = Color.Transparent,
            imageVector = Icons.Rounded.Facebook,
            contentDescription = null,
          )
        }
      )
    }
  )
}

@Preview
@Composable
fun CustomFacebookButtonPreview() {
  CustomFacebookButton() {

  }
}

