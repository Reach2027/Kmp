package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
    addCommonDeps: KotlinDependencyHandler.() -> Unit = { },
) = extension.apply {
    jvmToolchain(17)

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    jvm("desktop")

    applyDefaultHierarchyTemplate()

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs, "kotlinx-coroutines-core")
                implementation(libs, "koin-core")

                implementation(libs, "kermit")

//                implementation(libs, "androidx-annotation")
//                implementation(libs, "androidx-collection")

                addCommonDeps()
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs, "kotlinx-coroutines-test")
            }
        }
    }

}