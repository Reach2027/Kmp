plugins {
    alias(libs.plugins.reachKmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {

        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.jvm)
            implementation(libs.kotlinx.coroutines.android)
        }

        iosMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }

        wasmJsMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.jvm)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "com.reach.kmp.data.base.common"
}