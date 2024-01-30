package jp.gcreate.gradleshare.plugin

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import nl.littlerobots.vcu.plugin.VersionCatalogUpdateExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

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
            tasks.withType<DependencyUpdatesTask> {
                rejectVersionIf {
                    isNonStable(candidate.version) && !isNonStable(currentVersion)
                }
            }
        }
    }

    private fun Project.versionCatalogUpdate(action: VersionCatalogUpdateExtension.() -> Unit) {
        extensions.configure(action)
    }

    // see: https://github.com/ben-manes/gradle-versions-plugin#rejectversionsif-and-componentselection
    private fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}