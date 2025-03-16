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
    alias(libs.plugins.featureModule)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.featureData.bingwallpaper)

            implementation(libs.kotlinx.serialization.core)

            implementation(libs.coil)
            implementation(libs.coil.ktor)

            implementation(libs.haze)
            implementation(libs.haze.materials)
        }
    }
}

android {
    namespace = "com.reach.kmp.feature.bingwallpaper"
}