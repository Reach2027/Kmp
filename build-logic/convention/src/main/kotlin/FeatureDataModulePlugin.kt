import com.android.build.api.dsl.LibraryExtension
import com.reach.kmp.buildlogic.configureAndroid
import com.reach.kmp.buildlogic.configureKotlinMultiplatform
import com.reach.kmp.buildlogic.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureDataModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(getPluginId("kotlinMultiplatform"))

            apply(getPluginId("androidLibrary"))
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this)

            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(project(":data-core:common"))
                        implementation(project(":data-base:common"))
                    }
                }
            }
        }
        extensions.configure<LibraryExtension>(::configureAndroid)
    }
}