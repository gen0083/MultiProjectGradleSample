package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.kotlin
import jp.gcreate.gradleshare.dsl.library
import jp.gcreate.gradleshare.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KmpJvmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            kotlin {
                jvm()
            }
        }
    }
}
