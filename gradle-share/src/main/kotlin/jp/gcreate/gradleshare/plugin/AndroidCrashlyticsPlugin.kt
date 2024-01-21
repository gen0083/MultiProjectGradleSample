package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.implementation
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidCrashlyticsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.firebase.crashlytics")
            }
            dependencies {
                implementation(libs.library("firebaseCrashlytics"))
            }
        }
    }
}
