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
        register("Feature"){
            id = "com.reach.kmp.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("FeatureData"){
            id = "com.reach.kmp.feature.data"
            implementationClass = "FeatureDataConventionPlugin"
        }
        register("kotlinMultiplatform"){
            id = "com.reach.kmp.kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform"){
            id = "com.reach.kmp.compose.multiplatform"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
    }
}