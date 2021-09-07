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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericampire.android.androidstudycase.R


@Composable
fun CustomGoogleButton(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  onClick: () -> Unit
) {
  OutlinedButton(
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
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
            tint = Color.Unspecified,
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = null,
          )
          Text(
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.surface,
            text = stringResource(R.string.txt_connect_with_google)
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
fun CustomGoogleButtonPreview() {
  CustomGoogleButton {

  }
}
