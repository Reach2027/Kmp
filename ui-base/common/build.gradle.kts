plugins {
    alias(libs.plugins.reachCmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {

        }
    }
}

android {
    namespace = "com.reach.kmp.ui.base.common"
}