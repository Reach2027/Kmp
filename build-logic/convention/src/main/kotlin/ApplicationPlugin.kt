import com.android.build.api.dsl.ApplicationExtension
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

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(getPluginId("kotlinMultiplatform"))
            apply(getPluginId("composeMultiplatform"))
            apply(getPluginId("composeCompiler"))

            apply(getPluginId("androidApplication"))
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureKotlinMultiplatform(this, false)
            configureComposeMultiplatform(this)

            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(libs, "koin-core")
                        implementation(libs, "koin-compose")
                        implementation(libs, "koin-compose-navigation")
                    }
                }

                androidMain.dependencies {
                    implementation(libs, "androidx-activity-compose")
                }
            }
        }

        extensions.configure<ApplicationExtension> {
            configureAndroid(this)

            buildFeatures {
                compose = true
            }
        }
    }
}