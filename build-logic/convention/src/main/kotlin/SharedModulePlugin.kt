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

import com.android.build.api.dsl.LibraryExtension
import com.reach.kmp.buildlogic.configureAndroid
import com.reach.kmp.buildlogic.configureComposeMultiplatform
import com.reach.kmp.buildlogic.configureKotlinMultiplatform
import com.reach.kmp.buildlogic.getPluginId
import com.reach.kmp.buildlogic.implementation
import com.reach.kmp.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class SharedModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(getPluginId("kotlinMultiplatform"))
            apply(getPluginId("composeMultiplatform"))
            apply(getPluginId("composeCompiler"))

            apply(getPluginId("androidLibrary"))
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this, true)
            configureComposeMultiplatform(this)

            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(libs, "koin-compose")
                        implementation(libs, "koin-compose-navigation")
                    }
                }

                androidMain.dependencies {
                    implementation(libs, "androidx-activity-compose")
                }
            }
        }

        extensions.configure<LibraryExtension>(::configureAndroid)
    }
}
