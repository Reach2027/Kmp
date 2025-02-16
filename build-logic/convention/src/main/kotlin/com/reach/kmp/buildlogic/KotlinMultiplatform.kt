package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
    isLibrary: Boolean = true,
) = extension.apply {

    jvmToolchain(21)

    if (isLibrary) {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }

        iosX64()
        iosArm64()
        iosSimulatorArm64()

//        @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
//        wasmJs {
//            nodejs()
//            binaries.executable()
//        }

        jvm("desktop")
    }

    applyDefaultHierarchyTemplate()

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