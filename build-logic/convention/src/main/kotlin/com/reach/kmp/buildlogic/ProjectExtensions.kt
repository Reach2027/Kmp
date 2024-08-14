package com.reach.kmp.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.getPluginId(plugin: String) = libs.findPlugin(plugin).get().get().pluginId

internal fun KotlinDependencyHandler.implementation(libs: VersionCatalog, alias: String) {
    implementation(libs.findLibrary(alias).get())
}