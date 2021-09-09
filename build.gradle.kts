buildscript {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
  }
  dependencies {
    classpath(Libs.kotlin_gradle_plugin)
    classpath(Libs.gradle_plugin)
    classpath("com.dicedmelon.gradle:jacoco-android:0.1.5")
    classpath(Libs.hilt_gradle_plugin)
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}