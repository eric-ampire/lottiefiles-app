plugins {
  id("com.android.library")
  id("kotlin-android")
  kotlin("plugin.serialization") version "1.5.21"
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

  api(project(":util"))
  implementation(Libs.core_ktx)

  api(platform(Libs.kotlin_coroutine_bom))
  api(Libs.kotlin_coroutine_core)

  api(Libs.ktor_client_core)
  api(Libs.ktor_serialization)
  api(Libs.ktor_client_android)
  api(Libs.ktor_client_cio)

  api(Libs.joda_time)

  testImplementation(Libs.junit_jupiter_api)
  testImplementation(Libs.junit_jupiter_engine)

  testImplementation(Libs.mockk_core)

  api(Libs.hilt_android)
  kapt(Libs.hilt_android_compiler)

  api(Libs.timber)
}