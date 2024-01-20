package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.implementation
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class OssLicensesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.android.gms.oss-licenses-plugin")

            dependencies {
                implementation(libs.library("ossLicenses"))
            }
        }
    }
}
