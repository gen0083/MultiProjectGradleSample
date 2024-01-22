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
        register("androidKotlin") {
            id = "jp.gcreate.android.kotlin"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidKotlinPlugin"
        }
        register("androidCompose") {
            id = "jp.gcreate.android.compose"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidComposePlugin"
        }
        register("androidComposeWear") {
            id = "jp.gcreate.android.compose.wear"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidComposeWearPlugin"
        }
        register("androidHilt") {
            id = "jp.gcreate.android.hilt"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidHiltPlugin"
        }
        register("androidFirebase") {
            id = "jp.gcreate.android.firebase"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidFirebasePlugin"
        }
        register("androidCrashlytics") {
            id = "jp.gcreate.android.crashlytics"
            implementationClass = "jp.gcreate.gradleshare.plugin.AndroidCrashlyticsPlugin"
        }
        register("kotlinMultiplatform") {
            id = "jp.gcreate.kmp"
            implementationClass = "jp.gcreate.gradleshare.plugin.KmpPlugin"
        }
        register("kotlinMultiplatformAndroid") {
            id = "jp.gcreate.kmp.android"
            implementationClass = "jp.gcreate.gradleshare.plugin.KmpAndroidPlugin"
        }
        register("kotlinMultiplatformIos") {
            id = "jp.gcreate.kmp.ios"
            implementationClass = "jp.gcreate.gradleshare.plugin.KmpIosPlugin"
        }
        register("kotlinMultiplatformJvm") {
            id = "jp.gcreate.kmp.jvm"
            implementationClass = "jp.gcreate.gradleshare.plugin.KmpJvmPlugin"
        }
        register("ossLicensesPlugin") {
            id = "jp.gcreate.ossLicenses"
            implementationClass = "jp.gcreate.gradleshare.plugin.OssLicensesPlugin"
        }
    }
}