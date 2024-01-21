plugins {
    id("jp.gcreate.kmp")
    id("jp.gcreate.kmp.android")
    id("jp.gcreate.kmp.ios")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlinTest)
        }
    }
}

android {
    namespace = "jp.gcreate.sample.multiplatformsample.share"
}
