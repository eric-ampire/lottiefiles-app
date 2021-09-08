plugins {
    id("de.fayard.refreshVersions") version "0.20.0"
}

refreshVersions {
    enableBuildSrcLibs()
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    jcenter() // Todo: Warning this repository is going to shut down soon
  }
}
rootProject.name = "lottiefiles"
include(":app")
include(":util")
include(":i18n")
include(":data")
include(":domain")
