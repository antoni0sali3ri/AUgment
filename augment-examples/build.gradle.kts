import Dependencies.androidFrameworkTests
import Dependencies.androidUiTests
import Dependencies.androidx.lifecycle
import Dependencies.androidx.navigation

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildTools)

    defaultConfig {
        applicationId = "com.github.antoni0sali3ri.augment.examples"

        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    add("api", project(":augment-core"))
    add("api", project(":augment-core-ui"))

    implementation(Dependencies.kotlinStdLib)

    implementation(Dependencies.material)
    implementation(Dependencies.androidx.coreKtx)
    implementation(Dependencies.androidx.appcompat)
    implementation(Dependencies.androidx.constraintLayout)
    lifecycle()
    navigation()

    androidFrameworkTests()
    androidUiTests()
}

