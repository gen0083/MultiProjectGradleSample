package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KmpJvmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // kmpと同時に指定できないらしい(Cannot add extension with name 'kotlin', as there is an extension already registered with that name.)
                // https://youtrack.jetbrains.com/issue/KT-54178/Gradle-show-user-friendly-error-message-when-user-tries-to-apply-incompatible-Kotlin-plugins
                apply("org.jetbrains.kotlin.jvm")
            }
            kotlin {
                jvm()
            }
        }
    }
}
