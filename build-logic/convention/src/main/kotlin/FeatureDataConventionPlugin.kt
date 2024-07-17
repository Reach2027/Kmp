import com.android.build.api.dsl.LibraryExtension
import com.reach.kmp.buildlogic.configureAndroid
import com.reach.kmp.buildlogic.configureKotlinMultiplatform
import com.reach.kmp.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)

            apply(libs.findPlugin("androidLibrary").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this) {
                implementation(project(":data-core:common"))
                implementation(project(":data-base:common"))
            }
        }
        extensions.configure<LibraryExtension>(::configureAndroid)
    }
}