package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    extension: KotlinMultiplatformExtension
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
            }
        }
    }

}