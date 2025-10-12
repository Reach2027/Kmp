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

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKoin(
    kmpExtension: KotlinMultiplatformExtension,
    addKoinCompose: Boolean = false,
) {

    configureKsp()

    with(kmpExtension) {
        sourceSets.apply {
            commonMain {
                dependencies {
                    implementation(libs, "koin-core")
                    implementation(libs, "koin-core-coroutines")
                    implementation(libs, "koin-annotation")
                    if (addKoinCompose) {
                        implementation(libs, "koin-compose")
                        implementation(libs, "koin-compose-navigation")
                    }
                }
            }
        }

        // Koin KSP Common sourceSet
        sourceSets.named("commonMain").configure {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
    }

    // Koin KSP Tasks
    dependencies {
        val koinKsp = libs.findLibrary("koin-ksp").get()
        add("kspCommonMainMetadata", koinKsp)
    }

    extensions.configure<KspExtension> {
        arg("KOIN_CONFIG_CHECK", "true")
        arg("KOIN_LOG_TIMES", "true")
        arg("KOIN_DEFAULT_MODULE", "false")
        arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
        arg("KOIN_EXPORT_DEFINITIONS", "true")
    }
}
