pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Personal Investment Dashboard"
include(":app")
include(":core-ui")
include(":domain")
include(":core")
include(":data")
include(":graphing")
//include(":developer-utils")
include(":database")
include(":data-fake")
