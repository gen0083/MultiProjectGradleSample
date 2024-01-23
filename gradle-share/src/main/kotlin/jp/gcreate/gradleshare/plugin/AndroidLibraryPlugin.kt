package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.androidLibrary
import jp.gcreate.gradleshare.dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            androidLibrary {
                setupAndroid()
            }
        }
    }
}
