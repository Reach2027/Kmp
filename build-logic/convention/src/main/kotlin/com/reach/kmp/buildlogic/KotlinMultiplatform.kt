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
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    isConfigureEntry: Boolean = false,
    configure: KotlinMultiplatformExtension.() -> Unit = { },
) {
    configureAndroid()

    with(pluginManager) {
        apply(getPluginId("kotlinMultiplatform"))
    }

    extensions.configure<KotlinMultiplatformExtension> {
        jvmToolchain(17)

        // jvm
        jvm()

        // Android
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }

            publishLibraryVariants("release")
        }

        // iOS
        if (isConfigureEntry) {
            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64(),
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = "ComposeApp"
                    isStatic = true
                }
            }
        } else {
            iosX64()
            iosArm64()
            iosSimulatorArm64()
        }

        // wasm
//        @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
//        wasmJs {
//            nodejs()
//            binaries.executable()
//        }

        sourceSets.apply {
            commonMain {
                dependencies {
                    implementation(libs, "kotlinx-coroutines-core")

                    implementation(libs, "kermit")

                    implementation(libs, "androidx-annotation")
                    implementation(libs, "androidx-collection")
                }
            }

            commonTest {
                dependencies {
                    implementation(kotlin("test"))
                    implementation(libs, "kotlinx-coroutines-test")
                }
            }
        }

        configure()
    }

    configurePublish()
}
