/*
 * Copyright 2025 Reach Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        mavenLocal()
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

include(":app-android")
include(":app-desktop")
include(":app-wasm")

include(":shared")

include(":feature:bingwallpaper")
include(":feature:compose")
include(":feature:learn")

include(":ui-core:common")

include(":ui-base:common")

include(":feature-data:bingwallpaper")

include(":data-core:common")
include(":data-core:network")

include(":data-base:common")
