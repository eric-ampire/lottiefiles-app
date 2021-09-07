package com.ericampire.android.androidstudycase.util.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.utils.Utils
import org.zxconnect.android.beserve.util.R
import java.io.File


fun Context.downloadAndShare(
  url: String,
  onSuccess: () -> Unit,
  onError: (String?) -> Unit
) {

  val dirPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
  val fileName = url.substringAfterLast("/")
  val filePath = Utils.getPath(dirPath, fileName)
  val downloadRequest = PRDownloader.download(url, dirPath, fileName).build()

  downloadRequest.start(object : OnDownloadListener {
    override fun onDownloadComplete() {
      shareFile(filePath)
      onSuccess()
    }

    override fun onError(error: Error) {
      onError(error.serverErrorMessage)
    }
  })
}

fun Context.copyTextToClipboard(text: String) {
  val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
  val clipData = ClipData.newPlainText("text", text)
  clipboardManager.setPrimaryClip(clipData)
  Toast.makeText(this, "Link copied to clipboard", Toast.LENGTH_LONG).show()
}

private fun Context.shareFile(filePath: String) {
  val file = File(filePath)
  val fileUri = FileProvider.getUriForFile(
    this,
    this.applicationContext.packageName.toString() + ".provider",
    file
  )

  val shareIntent = ShareCompat.IntentBuilder(this)
    .setChooserTitle(getString(R.string.txt_share_lottie_file))
    .setStream(fileUri)
    .setType("application/pdf")
    .createChooserIntent()

  startActivity(shareIntent)
}