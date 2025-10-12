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
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

internal fun Project.configureKsp() {
    with(pluginManager) {
        apply(getPluginId("ksp"))
    }

    // ktlint format depends on koin ksp
    tasks.named("runKtlintFormatOverCommonMainSourceSet") {
        dependsOn("kspCommonMainKotlinMetadata")
    }

    // ktlint check depends on koin ksp
    tasks.named("runKtlintCheckOverCommonMainSourceSet") {
        dependsOn("kspCommonMainKotlinMetadata")
    }

    tasks.withType<KotlinCompilationTask<*>>().configureEach {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }

    afterEvaluate {
        tasks.filter {
            it.name.contains("SourcesJar", true)
        }.forEach {
            println("SourceJarTask====>${it.name}")
            it.dependsOn("kspCommonMainKotlinMetadata")
        }
    }
}
