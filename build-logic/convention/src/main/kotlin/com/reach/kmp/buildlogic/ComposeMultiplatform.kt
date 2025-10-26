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

package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    kmpExtension: KotlinMultiplatformExtension,
) {
    with(pluginManager) {
        apply(getPluginId("composeMultiplatform"))
        apply(getPluginId("composeCompiler"))
    }

    kmpExtension.apply {
        val composeDeps = extensions.getByType<ComposePlugin.Dependencies>()

        sourceSets.apply {
            commonMain {
                dependencies {
                    implementation(composeDeps.animation)
//                    implementation(composeDeps.material3)
                    implementation(composeDeps.materialIconsExtended)
                    implementation(composeDeps.foundation)
                    implementation(composeDeps.ui)
                    implementation(composeDeps.uiUtil)
                    implementation(composeDeps.components.resources)
                    implementation(composeDeps.components.uiToolingPreview)
                    implementation(composeDeps.runtime)
                    implementation(composeDeps.runtimeSaveable)

                    implementation(libs, "navigation")

                    implementation(libs, "lifecycle-common")
                    implementation(libs, "lifecycle-runtime")
                    implementation(libs, "lifecycle-viewmodel")
                    implementation(libs, "lifecycle-viewmodel-savedstate")

                    implementation(libs, "material3")
                    implementation(libs, "material3-adaptive")
                    implementation(libs, "material3-adaptive-layout")
                    implementation(libs, "material3-adaptive-navigation")

                    implementation(libs, "savedstate")
                    implementation(libs, "window-core")
                }
            }

            androidMain {
                dependencies {
                    implementation(composeDeps.uiTooling)
                    implementation(composeDeps.preview)
                }
            }
        }
    }
}
