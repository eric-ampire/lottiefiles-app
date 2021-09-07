package com.ericampire.android.androidstudycase.presentation.screen.preview.ui

import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.custom.LottieAnimationView
import com.ericampire.android.androidstudycase.presentation.custom.LottiePreviewDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState


@ExperimentalPermissionsApi
@Composable
fun PreviewScreen(navController: NavController) {

  var codeScanner: CodeScanner? = null
  var lottieFileUrl by remember { mutableStateOf("") }
  val context = LocalContext.current

  val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

  LaunchedEffect(true) {
    cameraPermissionState.launchPermissionRequest()
  }

  DisposableEffect(true) {
    onDispose {
      codeScanner?.stopPreview()
    }
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    content = {
      if (lottieFileUrl.isNotEmpty()) {
        LottiePreviewDialog(
          url = lottieFileUrl,
          onError = {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
          },
          onDismissRequest = {
            lottieFileUrl = ""
            codeScanner?.startPreview()
          }
        )
      }
      PermissionRequired(
        permissionState = cameraPermissionState,
        permissionNotGrantedContent = {
          PermissionNotAvailableContent(
            descriptionMessage = stringResource(R.string.txt_camera_permssion_required),
            onPermissionRequest = {
              cameraPermissionState.launchPermissionRequest()
            }
          )
        },
        permissionNotAvailableContent = {
          PermissionNotAvailableContent(
            descriptionMessage = stringResource(R.string.txt_permission_denied),
            onPermissionRequest = {
              cameraPermissionState.launchPermissionRequest()
            }
          )
        },
        content = {
          AndroidView(
            factory = { context ->
              val view = LayoutInflater.from(context).inflate(R.layout.code_scanner, null, false)
              val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
              codeScanner = CodeScanner(context, scannerView).apply {
                startPreview()
              }
              codeScanner?.setDecodeCallback {
                lottieFileUrl = it.text
              }
              view
            },
            update = { }
          )
        }
      )
    }
  )
}

@Composable
fun PermissionNotAvailableContent(
  descriptionMessage: String,
  onPermissionRequest: () -> Unit
) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
    content = {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
          LottieAnimationView(
            modifier = Modifier.size(200.dp),
            resId = R.raw.camera_moving
          )
          Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = descriptionMessage,
            textAlign = TextAlign.Center
          )
          Spacer(modifier = Modifier.height(8.dp))
          Button(
            modifier = Modifier
              .width(200.dp)
              .height(50.dp),
            shape = RoundedCornerShape(32.dp),
            onClick = onPermissionRequest,
            content = {
              Text(
                text = stringResource(R.string.txt_request),
                style = MaterialTheme.typography.h6
              )
            }
          )
        }
      )
    }
  )
}