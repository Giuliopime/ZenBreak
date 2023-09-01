rootProject.name = "ZenBreak"

include(":shared")
include(":shared-compose")
include(":androidApp")
include(":desktopApp")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}