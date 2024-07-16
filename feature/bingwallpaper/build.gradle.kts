plugins {
    alias(libs.plugins.reachKmp)
    alias(libs.plugins.reachCmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.featureData.bingwallpaper)
        }
    }
}

android {
    namespace = "com.reach.kmp.feature.bingwallpaper"
}