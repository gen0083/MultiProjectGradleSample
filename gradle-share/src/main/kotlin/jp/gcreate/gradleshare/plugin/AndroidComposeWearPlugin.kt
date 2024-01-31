package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.android
import jp.gcreate.gradleshare.dsl.androidTestImplementation
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
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidComposeWearPlugin : Plugin<Project> {
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
                implementation(libs.library("composeUiToolingPreview"))
                implementation(libs.library("androidxLifecycleLifecycleRuntimeKtx"))
                implementation(libs.library("androidxActivityActivityCompose"))
                // wear compose
                implementation(libs.library("wearComposeFoundation"))
                implementation(libs.library("wearComposeUiTooling"))
                implementation(libs.library("wearComposeMaterial"))
                // test dependencies
                testImplementation(libs.library("junit"))
                androidTestImplementation(platform(libs.library("composeBom")) as MinimalExternalModuleDependency)
                androidTestImplementation(libs.library("androidxTestExtJunit"))
                androidTestImplementation(libs.library("androidxTestEspressoEspressoCore"))
                androidTestImplementation(libs.library("composeUiTestJunit4"))
                debugImplementation(libs.library("composeUiTooling"))
                debugImplementation(libs.library("composeUiTestManifest"))
                lintChecks(libs.library("composeLintCheck"))
            }
        }
    }
}