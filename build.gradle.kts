plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinxSerialization) apply false

    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
}