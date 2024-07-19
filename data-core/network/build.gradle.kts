plugins {
    alias(libs.plugins.reachKmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.serialization.json)
        }

        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }

        wasmJsMain.dependencies {
            implementation(libs.ktor.js)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
    }
}

android {
    namespace = "com.reach.kmp.data.core.network"
}