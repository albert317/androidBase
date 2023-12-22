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

rootProject.name = "BaseProject"
include(":app")
include(":core")
include(":core:firebase")
include(":core:database")
include(":core:network")
include(":core:design-system")
include(":features")
include(":features:login")
include(":features:login:presentation")
include(":features:login:domain")
include(":features:login:infrastructure")
include(":features:login:usecase")
include(":core:navigation")
include(":features:splash")
include(":features:splash:presentation")
include(":features:splash:infrastructure")
include(":features:splash:domain")
include(":features:splash:usecase")
