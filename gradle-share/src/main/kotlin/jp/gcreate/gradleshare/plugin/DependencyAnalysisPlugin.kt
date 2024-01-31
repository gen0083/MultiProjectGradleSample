package jp.gcreate.gradleshare.plugin

import com.autonomousapps.DependencyAnalysisExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DependencyAnalysisPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.autonomousapps.dependency-analysis")
            }
            // https://github.com/autonomousapps/dependency-analysis-gradle-plugin/wiki/Customizing-plugin-behavior
            dependencyAnalysis {
                structure {
                    ignoreKtx(true)
                }
            }
        }
    }

    private fun Project.dependencyAnalysis(action: DependencyAnalysisExtension.() -> Unit) {
        extensions.configure(action)
    }
}