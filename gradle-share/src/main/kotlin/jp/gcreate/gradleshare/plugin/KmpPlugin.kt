package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.kotlin
import jp.gcreate.gradleshare.dsl.libs
import jp.gcreate.gradleshare.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class KmpPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // multiplatformとkotlin.jvmを同時に適用すると以下のエラーが発生する
                // Cannot add extension with name 'kotlin', as there is an extension already registered with that name.
                apply("org.jetbrains.kotlin.multiplatform")
            }
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = libs.version("jvmVersion")
            }
            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink>().configureEach {
                notCompatibleWithConfigurationCache("Configuration chache not supported for a system property read at configuration time")
            }
            kotlin {
                jvm()
            }
        }
    }
}
