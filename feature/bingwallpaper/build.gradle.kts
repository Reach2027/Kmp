plugins {
    alias(libs.plugins.reachFeature)
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