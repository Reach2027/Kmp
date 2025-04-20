pluginManagement {
    includeBuild("build-logic")
    repositories {
        // Compose multiplatform dev
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        // google and mavenCentral
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
        // gradle plugin
        maven("https://mirrors.tencent.com/nexus/repository/gradle-plugins/")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        // Compose multiplatform dev
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        // google and mavenCentral
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
        google()
        mavenCentral()
    }
}

rootProject.name = "Kmp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":androidApp")

include(":shared")

include(":feature:bingwallpaper")
include(":feature:compose")

include(":ui-core:common")

include(":ui-base:common")

include(":feature-data:bingwallpaper")

include(":data-core:common")
include(":data-core:network")

include(":data-base:common")