plugins {
    id("de.fayard.refreshVersions") version "0.23.0"
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
rootProject.name = "lottiefiles-app"
include(":app")
include(":util")
include(":i18n")
include(":data")
include(":domain")
