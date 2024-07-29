
pluginManagement {
    includeBuild("build-logic")
    repositories {
        // Compose multiplatform
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        // google and mavenCentral
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
        maven("https://mirrors.tencent.com/nexus/repository/gradle-plugins/")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        // Compose multiplatform
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/") // google and mavenCentral
        google()
        mavenCentral()
    }
}

rootProject.name = "Kmp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":composeApp")

include(":feature:bingwallpaper")

include(":ui-core:common")

include(":ui-base:common")

include(":feature-data:bingwallpaper")

include(":data-core:common")
include(":data-core:network")

include(":data-base:common")