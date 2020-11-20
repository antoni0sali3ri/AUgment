import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.api(dependency: String) {
    add("api", dependency)
}

fun DependencyHandlerScope.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandlerScope.testRuntimeOnly(dependency: String) {
    add("testRuntimeOnly", dependency)
}

fun DependencyHandlerScope.androidTestRuntimeOnly(dependency: String) {
    add("androidTestRuntimeOnly", dependency)
}
