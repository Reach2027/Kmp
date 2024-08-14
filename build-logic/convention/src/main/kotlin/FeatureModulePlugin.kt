import com.android.build.api.dsl.LibraryExtension
import com.reach.kmp.buildlogic.configureAndroid
import com.reach.kmp.buildlogic.configureComposeMultiplatform
import com.reach.kmp.buildlogic.configureKotlinMultiplatform
import com.reach.kmp.buildlogic.getPluginId
import com.reach.kmp.buildlogic.implementation
import com.reach.kmp.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(getPluginId("kotlinMultiplatform"))
            apply(getPluginId("composeMultiplatform"))
            apply(getPluginId("composeCompiler"))

            apply(getPluginId("androidLibrary"))
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this)
            configureComposeMultiplatform(this)

            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(project(":data-core:common"))
                        implementation(project(":data-base:common"))
                        implementation(project(":ui-core:common"))
                        implementation(project(":ui-base:common"))

                        implementation(libs, "koin-compose")
                        implementation(libs, "koin-compose-viewmodel")
                    }
                }
            }
        }

        extensions.configure<LibraryExtension>(::configureAndroid)
    }

}