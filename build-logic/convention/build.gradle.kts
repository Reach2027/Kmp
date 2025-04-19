plugins {
    `kotlin-dsl`
}

group = "com.reach.kmp.buildlogic"

dependencies {
    compileOnly(libs.plugins.kotlinMultiplatform.toDep())
    compileOnly(libs.plugins.composeMultiplatform.toDep())
    compileOnly(libs.plugins.composeCompiler.toDep())
    compileOnly(libs.plugins.kotlinxSerialization.toDep())

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
        register("ApplicationPlugin") {
            id = "com.reach.kmp.application"
            implementationClass = "ApplicationPlugin"
        }

        register("FeatureModulePlugin") {
            id = "com.reach.kmp.feature.module"
            implementationClass = "FeatureModulePlugin"
        }

        register("FeatureDataModulePlugin") {
            id = "com.reach.kmp.feature.data.module"
            implementationClass = "FeatureDataModulePlugin"
        }

        register("UiCoreModulePlugin") {
            id = "com.reach.kmp.ui.core.module"
            implementationClass = "UiCoreModulePlugin"
        }
        register("UiBaseModulePlugin") {
            id = "com.reach.kmp.ui.base.module"
            implementationClass = "UiBaseModulePlugin"
        }

        register("DataCoreModulePlugin") {
            id = "com.reach.kmp.data.core.module"
            implementationClass = "DataCoreModulePlugin"
        }
        register("DataBaseModulePlugin") {
            id = "com.reach.kmp.data.base.module"
            implementationClass = "DataBaseModulePlugin"
        }
    }
}