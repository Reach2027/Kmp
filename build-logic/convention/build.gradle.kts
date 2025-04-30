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
plugins {
    `kotlin-dsl`
}

group = "com.reach.kmp.buildlogic"

dependencies {
    compileOnly(libs.plugins.kotlinMultiplatform.toDep())
    compileOnly(libs.plugins.composeMultiplatform.toDep())
    compileOnly(libs.plugins.composeCompiler.toDep())
    compileOnly(libs.plugins.kotlinxSerialization.toDep())
    compileOnly(libs.plugins.ksp.toDep())

    compileOnly(libs.plugins.androidApplication.toDep())
    compileOnly(libs.plugins.androidLibrary.toDep())
}

fun Provider<PluginDependency>.toDep() = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("SharedModulePlugin") {
            id = "com.reach.kmp.shared"
            implementationClass = "SharedModulePlugin"
        }

        register("FeatureModulePlugin") {
            id = "com.reach.kmp.feature"
            implementationClass = "FeatureModulePlugin"
        }

        register("FeatureDataModulePlugin") {
            id = "com.reach.kmp.feature.data"
            implementationClass = "FeatureDataModulePlugin"
        }

        register("UiCoreModulePlugin") {
            id = "com.reach.kmp.ui.core"
            implementationClass = "UiCoreModulePlugin"
        }
        register("UiBaseModulePlugin") {
            id = "com.reach.kmp.ui.base"
            implementationClass = "UiBaseModulePlugin"
        }

        register("DataCoreModulePlugin") {
            id = "com.reach.kmp.data.core"
            implementationClass = "DataCoreModulePlugin"
        }
        register("DataBaseModulePlugin") {
            id = "com.reach.kmp.data.base"
            implementationClass = "DataBaseModulePlugin"
        }
    }
}
