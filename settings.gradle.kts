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
include(":core:common")
include(":core:database")
include(":core:design-system")
include(":core:firebase")
include(":core:navigation")
include(":core:network")
include(":features")
include(":features:login")
include(":features:login:infrastructure")
include(":features:login:domain")
include(":features:login:presentation")
include(":features:login:usecase")
include(":features:splash")
include(":features:splash:infrastructure")
include(":features:splash:domain")
include(":features:splash:presentation")
include(":features:splash:usecase")
include(":core:common:presentation")
