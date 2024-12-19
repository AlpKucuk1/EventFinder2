pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // Ensure settings.gradle controls repositories
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EventFinder"
include(":app")
