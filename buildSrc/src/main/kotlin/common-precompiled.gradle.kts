plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(App.compileSdk)
    buildToolsVersion(App.buildTools)

    defaultConfig {
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = App.testRunner
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
}

dependencies {
    add("implementation", fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    add("implementation", Deps.kotlinStdLib)

    add("implementation", Deps.coreKtx)
    add("implementation", Deps.appcompat)

    add("testImplementation", Deps.junit5)
    add("androidTestImplementation", Deps.extJunit)
    add("androidTestImplementation", Deps.espresso)
}