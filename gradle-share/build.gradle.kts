// https://docs.gradle.org/current/samples/sample_convention_plugins.html#compiling_convention_plugins
plugins {
    `kotlin-dsl`
}

group = "jp.gcreate.gradleshare"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
// 最近のAndroidGradlePluginはjvmTarget11が要求されるため
compileKotlin.kotlinOptions.jvmTarget = "11"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.bundles.plugins)
    // https://github.com/google/dagger/issues/3068#issuecomment-1470534930
    implementation(libs.javaPoet)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "jp.gcreate.android.application"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidApplicationPlugin"
        }
    }
}