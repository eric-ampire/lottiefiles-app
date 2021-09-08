plugins {
  id("com.android.library")
  id("kotlin-android")
  kotlin("kapt")
}

android {
  compileSdk = Apps.compileSdk
  buildToolsVersion = Apps.buildToolsVersion

  defaultConfig {
    minSdk = Apps.minSdk
    targetSdk = Apps.targetSdk

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(platform(Libs.kotlin_coroutine_bom))
  implementation(Libs.kotlin_coroutine_core)
  implementation(Libs.kotlin_coroutine_test)

  implementation(Libs.junit_jupiter_api)
  implementation(Libs.junit_jupiter_engine)

  testImplementation(Libs.mockk_core)
}