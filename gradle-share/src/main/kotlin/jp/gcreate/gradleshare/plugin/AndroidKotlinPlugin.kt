package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.android
import jp.gcreate.gradleshare.dsl.implementation
import jp.gcreate.gradleshare.dsl.kotlinOptions
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.version
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = libs.version("jvmVersion")
            }

            android {
                kotlinOptions {
                    // Treat all Kotlin warnings as errors (disabled by default)
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf(
//              "-opt-in=kotlin.RequiresOptIn",
                        // Enable experimental coroutines APIs, including Flow
//              "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-Xcontext-receivers"
                    )

                    jvmTarget = libs.version("jvmVersion")
                }
            }
            dependencies {
                implementation(libs.library("kotlinxCoroutinesCore"))
                // Fix https://youtrack.jetbrains.com/issue/KT-41821
                implementation(libs.library("kotlinxAtomicfu"))
            }
        }
    }
}
