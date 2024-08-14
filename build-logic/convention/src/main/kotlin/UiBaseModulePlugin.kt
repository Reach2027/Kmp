import com.android.build.api.dsl.LibraryExtension
import com.reach.kmp.buildlogic.configureAndroid
import com.reach.kmp.buildlogic.configureComposeMultiplatform
import com.reach.kmp.buildlogic.configureKotlinMultiplatform
import com.reach.kmp.buildlogic.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class UiBaseModulePlugin : Plugin<Project> {
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
        }

        extensions.configure<LibraryExtension>(::configureAndroid)
    }
}