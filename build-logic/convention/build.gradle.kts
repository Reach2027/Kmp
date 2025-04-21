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
