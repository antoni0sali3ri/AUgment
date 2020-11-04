object App {
    const val minSdk = 16
    const val targetSdk = 30
    const val compileSdk = targetSdk
    const val versionCode = 1
    const val versionName = "0.1-SNAPSHOT"
    const val buildTools = "30.0.2"

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {

    const val gradle = "4.1.0"
    const val kotlin = "1.4.10"

    const val coreKtx = "1.3.2"
    const val appcompat = "1.2.0"

    const val junit4 = "4.12"
    const val junit5 = "5.7.0"
    const val extJunit = "1.1.2"
    const val espresso = "3.3.0"
}

object Plugins {

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Deps {
    // KOTLIN
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // ANDROID
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    // TEST
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"
    const val extJunit = "androidx.text.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}