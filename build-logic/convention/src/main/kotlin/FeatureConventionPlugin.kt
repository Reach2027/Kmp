import com.android.build.api.dsl.LibraryExtension
import com.reach.kmp.buildlogic.configureAndroid
import com.reach.kmp.buildlogic.configureComposeMultiplatform
import com.reach.kmp.buildlogic.configureKotlinMultiplatform
import com.reach.kmp.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeCompiler").get().get().pluginId)

            apply(libs.findPlugin("androidLibrary").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this) {
                implementation(project(":data-core:common"))
                implementation(project(":data-base:common"))
            }
            configureComposeMultiplatform(this) {
                implementation(project(":ui-core:common"))
                implementation(project(":ui-base:common"))
            }
        }

        extensions.configure<LibraryExtension>(::configureAndroid)
    }
}