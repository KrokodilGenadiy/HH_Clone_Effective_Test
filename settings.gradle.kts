pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "HH_Clone_Effective_Test"
include(":app")
include(":core:data")
include(":core:ui")
include(":core:utils")
include(":login:ui")
include(":search:data")
include(":search:ui")
include(":search:domain")
include(":search:di")
include(":details:ui")
include(":favorites:ui")
include(":favorites:domain")
include(":favorites:data")
include(":favorites:di")
include(":core:domain")
include(":details:di")
include(":profile:ui")
include(":replies:ui")
include(":messages:ui")
