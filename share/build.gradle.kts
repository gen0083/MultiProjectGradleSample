plugins {
    id("jp.gcreate.kmp")
    id("jp.gcreate.kmp.android")
    id("jp.gcreate.kmp.ios")
    id("jp.gcreate.kmp.jvm")
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
