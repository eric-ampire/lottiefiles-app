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
        classpath(Libs.hilt_gradle_plugin)
        classpath(Libs.play_service_plugin)
        classpath(Libs.firebase_crashlytics_plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}