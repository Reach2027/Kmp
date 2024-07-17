plugins {
    alias(libs.plugins.reachKmp)
    alias(libs.plugins.reachCmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.featureData.bingwallpaper)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
        }
    }
}

android {
    namespace = "com.reach.kmp.feature.bingwallpaper"
}