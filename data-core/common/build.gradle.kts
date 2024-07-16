plugins {
    alias(libs.plugins.reachKmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {

        }
    }
}

android {
    namespace = "com.reach.kmp.data.core.common"
}