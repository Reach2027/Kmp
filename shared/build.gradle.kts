/*
 * Copyright 2024 Reach Project
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

plugins {
    alias(libs.plugins.sharedModule)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.uiCore.common)
            implementation(projects.feature.bingwallpaper)
            implementation(projects.feature.compose)
            implementation(projects.feature.learn)

            implementation(libs.kotlinx.serialization.core)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.jvm)
            implementation(libs.kotlinx.coroutines.android)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.jvm)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "com.reach.kmp.shared"
}
