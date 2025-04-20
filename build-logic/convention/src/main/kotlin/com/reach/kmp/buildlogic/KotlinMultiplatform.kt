package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
    isConfigureEntry: Boolean = false,
) = extension.apply {

    jvmToolchain(21)

    if (isConfigureEntry) {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }

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

        jvm("desktop")
    } else {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }

        // iOS
        iosX64()
        iosArm64()
        iosSimulatorArm64()

        // wasm
//        @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
//        wasmJs {
//            nodejs()
//            binaries.executable()
//        }

        jvm()
    }

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs, "kotlinx-coroutines-core")

                implementation(libs, "koin-core")
                implementation(libs, "koin-core-coroutines")

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

}
