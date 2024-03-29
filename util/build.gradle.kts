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

  api(project(":i18n"))
  api("org.reduxkotlin:redux-kotlin-threadsafe:0.5.5")

  implementation(Libs.core_ktx)
  api(Libs.androidx_lifecycle_viewmodel_ktx)

  api(Libs.room_runtime)
  api(Libs.room_ktx)

  api(platform(Libs.kotlin_coroutine_bom))
  api(Libs.kotlin_coroutine_core)
  implementation(Libs.kotlin_coroutine_test)

  api(Libs.mavericks_core)

  implementation(Libs.junit_jupiter_api)
  implementation(Libs.junit_jupiter_engine)

  testImplementation(Libs.mockk_core)

  api(Libs.hilt_android)
  kapt(Libs.hilt_android_compiler)

  api(Libs.timber)
  api(Libs.pr_downloader)
}