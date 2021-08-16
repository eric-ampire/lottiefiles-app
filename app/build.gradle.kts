import de.fayard.refreshVersions.core.versionFor


plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("com.google.devtools.ksp") version "1.5.21-1.0.0-beta07"
}

kapt {
    correctErrorTypes = true
    javacOptions {
        option("-Adagger.fastInit=ENABLED")
        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

android {
    compileSdk = Apps.compileSdk
    buildToolsVersion = Apps.buildToolsVersion

    defaultConfig {
        applicationId = Apps.applicationId
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.ui)
    }
    lint {
        isAbortOnError = false
    }
    packagingOptions {
        resources {
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/licenses/ASM"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/LGPL2.1"
            excludes += "win32-x86/attach_hotspot_windows.dll"
            excludes += "win32-x86-64/attach_hotspot_windows.dll"
        }
    }
}

dependencies {

    implementation(project(":util"))

    implementation(Libs.activity_compose)
    implementation(Libs.navigation_compose)
    implementation(Libs.core_ktx)
    implementation(Libs.preference_ktx)
    implementation(Libs.appcompat)
    implementation(Libs.startup_runtime)
    implementation(Libs.google_material)

    implementation(Libs.compose_compiler)
    implementation(Libs.compose_ui_tooling)
    implementation(Libs.compose_ui_tooling_preview)
    implementation(Libs.compose_ui)
    implementation(Libs.compose_ui_viewbinding)
    implementation(Libs.compose_material)
    implementation(Libs.compose_runtime_livedata)
    implementation(Libs.compose_material_icons_extended)
    androidTestImplementation(Libs.compose_ui_test_junit4)

    implementation(Libs.accompanist_insets)
    implementation(Libs.accompanist_pager)
    implementation(Libs.accompanist_glide)
    implementation(Libs.accompanist_pager_indicators)
    implementation(Libs.accompanist_placeholder)
    implementation(Libs.accompanist_navigation_animation)

    implementation(kotlin("reflect"))

    implementation(platform(Libs.kotlin_coroutine_bom))
    testImplementation(Libs.kotlin_coroutine_test)

    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)

    implementation(Libs.lifecycle_runtime_ktx)

    testImplementation(Libs.junit_jupiter_api)
    testImplementation(Libs.junit_jupiter_engine)

    implementation(Libs.koin_android)
    implementation(Libs.koin_androidx_compose)
    implementation(Libs.koin_androidx_workmanager)
    testImplementation(Libs.koin_test_junit5)

    implementation(Libs.ksp_api)

    implementation(Libs.orbit_mvi_core)
    implementation(Libs.orbit_mvi_viewmodel)
    testImplementation(Libs.orbit_mvi_test)

    ksp(Libs.room_compiler)
    implementation(Libs.room_runtime)
    testImplementation(Libs.room_testing)

    testImplementation(Libs.mockk_core)
    androidTestImplementation(Libs.mockk_android)

    debugImplementation(Libs.leakcanary_android)

    implementation(Libs.timber)
}