plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  compileSdk = Apps.compileSdk
  defaultConfig {
    minSdk = Apps.minSdk
  }
}