package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.android
import jp.gcreate.gradleshare.dsl.implementation
import jp.gcreate.gradleshare.dsl.ksp
import jp.gcreate.gradleshare.dsl.kspTest
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            android {
                packagingOptions {
                    resources {
                        excludes += "META-INF/gradle/incremental.annotation.processors"
                    }
                }
            }
            dependencies {
                implementation(libs.library("daggerHiltAndroid"))
                // https://issuetracker.google.com/issues/237567009
                implementation(libs.library("androidxFragment"))
                ksp(libs.library("daggerHiltAndroidCompiler"))
                testImplementation(libs.library("daggerHiltAndroidTesting"))
                kspTest(libs.library("daggerHiltAndroidTesting"))
            }
        }
    }
}
