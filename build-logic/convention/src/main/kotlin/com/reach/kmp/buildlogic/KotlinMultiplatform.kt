package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
    isLibrary: Boolean = true
) = extension.apply {

    jvmToolchain(17)

    if (isLibrary) {
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
        wasmJs()

        jvm("desktop")
    }

    applyDefaultHierarchyTemplate()

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs, "kotlinx-coroutines-core")
                implementation(libs, "koin-core")

                implementation(libs, "kermit")

//                implementation(libs, "androidx-annotation")
//                implementation(libs, "androidx-collection")
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