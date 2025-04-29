pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Parcial2"
include(":app")
