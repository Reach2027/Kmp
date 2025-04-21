import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinxSerialization) apply false

    alias(libs.plugins.ktlintPlugin)

    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("1.5.0")
        debug.set(true)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(true)
//        enableExperimentalRules.set(true)

//        additionalEditorconfig.set(
//            mapOf(
//                "max_line_length" to "20"
//            )
//        )

        reporters {
            reporter(ReporterType.HTML)
        }

        filter {
            include("**/kotlin/**")
            exclude("**/build/**")
        }
    }

    dependencies {
        ktlintRuleset("com.twitter.compose.rules:ktlint:0.0.26")
    }
}
