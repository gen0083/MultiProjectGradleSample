package jp.gcreate.sample.multiplatformsample.share

class JvmPlatform(override val name: String = "JVM") : Platform {

}
actual fun getPlatform(): Platform = JvmPlatform()