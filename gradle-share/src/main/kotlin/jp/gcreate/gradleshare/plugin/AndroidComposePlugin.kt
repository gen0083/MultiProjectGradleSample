package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.android
import jp.gcreate.gradleshare.dsl.buildComposeMetricsParameters
import jp.gcreate.gradleshare.dsl.debugImplementation
import jp.gcreate.gradleshare.dsl.implementation
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.lintChecks
import jp.gcreate.gradleshare.dsl.testImplementation
import jp.gcreate.gradleshare.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("unused")
class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            android {
                buildFeatures.compose = true
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.version("composeCompiler")
                }
            }

            // to enable metrics, pass argument "-PenableComposeCompilerMetrics=true -PenableComposeCompilerReports=true"
            // e.g.) ./gradlew assembleRelease -P enableComposeCompilerMetrics=true -P enableComposeCompilerReports=true
            tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions {
                    freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
                }
            }

            dependencies {
                implementation(platform(libs.library("composeBom")))
                implementation(libs.library("androidxCoreKtx"))
                implementation(libs.library("composeUi"))
                implementation(libs.library("composeMaterial"))
                implementation(libs.library("composeMaterial3"))
                implementation(libs.library("composeUiToolingPreview"))
                implementation(libs.library("androidxLifecycleLifecycleRuntimeKtx"))
                implementation(libs.library("androidxActivityActivityCompose"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("androidxTestExtJunit"))
                testImplementation(libs.library("androidxTestEspressoEspressoCore"))
                testImplementation(libs.library("composeUiTestJunit4"))
                debugImplementation(libs.library("composeUiTooling"))
                debugImplementation(libs.library("composeUiTestManifest"))
                lintChecks(libs.library("composeLintCheck"))
            }
        }
    }
}

