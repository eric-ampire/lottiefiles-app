plugins {
    id("de.fayard.refreshVersions") version "0.11.0"
}

refreshVersions {
    enableBuildSrcLibs()
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    jcenter() // Warning: this repository is going to shut down soon
  }
}
rootProject.name = "android-study-case"
include(":app")
include(":util")
include(":i18n")
include(":data")
include(":domain")
