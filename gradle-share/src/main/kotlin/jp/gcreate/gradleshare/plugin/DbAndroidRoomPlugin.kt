package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.android
import jp.gcreate.gradleshare.dsl.implementation
import jp.gcreate.gradleshare.dsl.kotlin
import jp.gcreate.gradleshare.dsl.ksp
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DbAndroidRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("androidx.room")
                apply("com.google.devtools.ksp")
            }
            android {
                dependencies {
                    implementation(libs.library("roomRuntime"))
                    ksp(libs.library("roomCompiler"))
                    implementation(libs.library("roomKtx"))
                    implementation(libs.library("roomPaging"))
                    testImplementation(libs.library("roomTesting"))
                }
            }
        }
    }
}