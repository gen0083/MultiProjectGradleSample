package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.androidLibrary
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.version
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            androidLibrary {
                namespace?.let {
                    this.namespace = it
                }
                compileSdk = libs.version("compileSdk").toInt()

                defaultConfig {
                    minSdk = libs.version("minSdk").toInt()
                    // libraryではtargetSdkは削除される予定（gradle9.0から）→たぶん依存側のtargetSdkが使われるんだと思う
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
