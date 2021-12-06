plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    maven
    `maven-publish`
}

repositories {
    jcenter()
}


buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}

repositories {
    jcenter()
    google()
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.4"
}

dependencies {
    val gradleVersion = "4.1.0"
    val kotlinVersion = "1.4.10"
    implementation("com.android.tools.build:gradle:$gradleVersion")
    implementation("com.android.tools.build:gradle-api:$gradleVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}

