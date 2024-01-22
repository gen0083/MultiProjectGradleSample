@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("jp.gcreate.android.application")
    id("jp.gcreate.android.kotlin")
    id("jp.gcreate.android.compose.wear")
}

android {
    namespace = "jp.gcreate.sample.multiprojectsample.wear"

    defaultConfig {
        applicationId = "jp.gcreate.sample.multiprojectsample.wear"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.play.services.wearable)
    implementation(libs.androidxSplashScreen)
}