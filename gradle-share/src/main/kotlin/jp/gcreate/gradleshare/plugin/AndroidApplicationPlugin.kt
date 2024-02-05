package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.android
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.version
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            android {
                namespace?.let {
                    this.namespace = it
                }
                compileSdk = libs.version("compileSdk").toInt()

                defaultConfig {
                    minSdk = libs.version("minSdk").toInt()
                    targetSdk = libs.version("targetSdk").toInt()
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.toVersion(libs.version("jvmVersion").toInt())
                    targetCompatibility = JavaVersion.toVersion(libs.version("jvmVersion").toInt())
                    isCoreLibraryDesugaringEnabled = true
                }
                dependencies {
                    add("coreLibraryDesugaring", libs.library("androidDesugarJdkLibs"))
                }
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }
            }
        }
    }
}