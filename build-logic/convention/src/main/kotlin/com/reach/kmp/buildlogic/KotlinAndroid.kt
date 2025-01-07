package com.reach.kmp.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureAndroid(
    extension: CommonExtension<*, *, *, *, *, *>,
) = extension.apply {
    compileSdk = libs.findVersion("androidCompileSdk").get().requiredVersion.toInt()

    if (this is ApplicationExtension) {
        defaultConfig.targetSdk = libs.findVersion("androidTargetSdk").get().requiredVersion.toInt()
    } else {
        lint.targetSdk = libs.findVersion("androidTargetSdk").get().requiredVersion.toInt()
    }

    defaultConfig {
        minSdk = libs.findVersion("androidMinSdk").get().requiredVersion.toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}