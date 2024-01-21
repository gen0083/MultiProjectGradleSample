package jp.gcreate.sample.multiplatformsample.share

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform