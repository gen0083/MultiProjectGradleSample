package jp.gcreate.gradleshare.plugin

import nl.littlerobots.vcu.plugin.PinConfiguration
import nl.littlerobots.vcu.plugin.VersionCatalogConfig
import nl.littlerobots.vcu.plugin.VersionCatalogUpdateExtension
import nl.littlerobots.vcu.plugin.versionCatalogUpdate
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.targets.js.npm.versionToNpmRanges

class VersionCatalogUpdatePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.github.ben-manes.versions")
                apply("nl.littlerobots.version-catalog-update")
            }
            versionCatalogUpdate {
                sortByKey.set(false)
                pin {
                    versions.addAll("targetSdk")
                }
                keep {
                    keepUnusedLibraries.set(true)
                    keepUnusedPlugins.set(true)
                    keepUnusedVersions.set(true)
                }
            }
        }
    }

    private fun Project.versionCatalogUpdate(action: VersionCatalogUpdateExtension.() -> Unit) {
        extensions.configure(action)
    }
}