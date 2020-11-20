import Dependencies.androidx.room
import Dependencies.junit5

plugins {
    id("common-precompiled")
}

kotlin {
    explicitApi()
}

dependencies {
    add("api", project(":augment-core"))
    junit5()

    room()
}