import Dependencies.junit5

plugins {
    id("common-precompiled")
}

kotlin {
    explicitApi()
}

android {
    packagingOptions {
        exclude("META-INF/LICENSE*")
    }
}

dependencies {
    add("api", project(":augment-core"))
    junit5()
}