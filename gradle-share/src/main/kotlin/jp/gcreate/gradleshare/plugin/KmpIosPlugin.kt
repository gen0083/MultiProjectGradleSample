package jp.gcreate.gradleshare.plugin

import jp.gcreate.gradleshare.dsl.Arch
import jp.gcreate.gradleshare.dsl.activeArch
import jp.gcreate.gradleshare.dsl.kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("unused")
class KmpIosPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.louiscad.complete-kotlin")
            }

            kotlin {
                // TODO: remove after 1.5 release
                //  https://github.com/JetBrains/compose-multiplatform/issues/3135#issuecomment-1655877617
                val simulatorLinkerOptions = listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics",
                )
                when (activeArch) {
                    Arch.ARM -> iosSimulatorArm64 {
                        binaries.forEach {
                            it.freeCompilerArgs += simulatorLinkerOptions
                        }
                    }
                    Arch.X86 -> iosX64()
                    Arch.ALL -> {
                        iosArm64()
                        iosX64()
                        iosSimulatorArm64 {
                            binaries.forEach {
                                it.freeCompilerArgs += simulatorLinkerOptions
                            }
                        }
                    }
                }
                with(sourceSets) {
                    create("iosMain") {
                        dependsOn(getByName("commonMain"))
                        maybeCreate("iosArm64Main").dependsOn(this)
                        maybeCreate("iosX64Main").dependsOn(this)
                        maybeCreate("iosSimulatorArm64Main").dependsOn(this)
                    }

                    create("iosTest") {
                        dependsOn(getByName("commonTest"))
                        maybeCreate("iosArm64Test").dependsOn(this)
                        maybeCreate("iosX64Test").dependsOn(this)
                        maybeCreate("iosSimulatorArm64Test").dependsOn(this)
                    }
                }

                targets.withType<KotlinNativeTarget> {
                    // export kdoc to header file
                    // https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
                    compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
                }
            }
        }
    }
}
