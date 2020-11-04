apply {
    from("../sub-common.gradle.kts")
}

dependencies {
    add("api", project(":core"))
}