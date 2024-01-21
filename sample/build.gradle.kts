plugins {
    id("jp.gcreate.android.application")
    id("jp.gcreate.android.kotlin")
    id("jp.gcreate.android.compose")
    id("jp.gcreate.ossLicenses")
}

android {
    namespace = "jp.gcreate.sample.multiprojectgradlesample"

    defaultConfig {
        applicationId = "jp.gcreate.sample.multiprojectgradlesample"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}