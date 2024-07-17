package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

internal fun Project.configureComposeMultiplatform(
    extension: KotlinMultiplatformExtension,
    addCommonDeps: KotlinDependencyHandler.() -> Unit = { },
) = extension.apply {

    val composeDeps = extensions.getByType<ComposeExtension>().dependencies

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

//                implementation(libs, "androidx-lifecycle-runtime")
//                implementation(libs, "androidx-lifecycle-viewmodel")
//                implementation(libs, "androidx-lifecycle-common")

                addCommonDeps()
            }
        }
    }

}