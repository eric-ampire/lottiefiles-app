package com.ericampire.android.androidstudycase.presentation.screen.preview.ui

import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.custom.LottiePreviewDialog


@Composable
fun PreviewScreen(navController: NavController) {

  var codeScanner: CodeScanner? = null
  var lottieFileUrl by remember { mutableStateOf("") }
  val context = LocalContext.current

  DisposableEffect(true) {
    onDispose {
      codeScanner?.stopPreview()
    }
  }

  Scaffold(
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