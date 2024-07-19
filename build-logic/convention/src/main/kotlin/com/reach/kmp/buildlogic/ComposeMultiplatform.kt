package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

internal fun Project.configureComposeMultiplatform(
    extension: KotlinMultiplatformExtension,
    addCommonDeps: KotlinDependencyHandler.() -> Unit = { },
) = extension.apply {

    val composeDeps = extensions.getByType<ComposeExtension>().dependencies

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        enableStrongSkippingMode.set(true)
        enableNonSkippingGroupOptimization.set(true)
    }

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(composeDeps.animation)
                implementation(composeDeps.material3)
                implementation(composeDeps.materialIconsExtended)
                implementation(composeDeps.foundation)
                implementation(composeDeps.ui)
                implementation(composeDeps.uiUtil)
                implementation(composeDeps.components.resources)
                implementation(composeDeps.components.uiToolingPreview)
                implementation(composeDeps.runtime)
                implementation(composeDeps.runtimeSaveable)

                implementation(libs, "core-bundle")

                implementation(libs, "lifecycle-common")
                implementation(libs, "lifecycle-runtime")
                implementation(libs, "lifecycle-viewmodel")
                implementation(libs, "lifecycle-viewmodel-savedstate")

                implementation(libs, "navigation")

                addCommonDeps()
            }

            androidMain.dependencies {
                implementation(composeDeps.preview)
            }
        }
    }

}