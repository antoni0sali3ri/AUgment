import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {
    // KOTLIN
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // ANDROID
    const val material = "com.google.android.material:material:${Versions.material}"

    object androidx {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidx.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx.appcompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx.constraintLayout}"

        const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx.lifecycle}"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx.lifecycle}"

        const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.androidx.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui:${Versions.androidx.navigation}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidx.navigation}"

        fun DependencyHandlerScope.lifecycle() {
            implementation(lifecycleLiveDataKtx)
            implementation(lifecycleViewModelKtx)
        }

        fun DependencyHandlerScope.navigation() {
            implementation(navigationFragment)
            implementation(navigationFragmentKtx)
            implementation(navigationUi)
            implementation(navigationUiKtx)
        }
    }

    // TEST
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"
    const val junit5engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val junit5params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val robolectric = "androidx.test:core:${Versions.robolectric}"
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"

    fun DependencyHandlerScope.junit5() {
        testImplementation(junit5)
        testImplementation(junit5params)
        testRuntimeOnly(junit5engine)
    }

    fun DependencyHandlerScope.junit5android() {
        androidTestImplementation(junit5)
        androidTestImplementation(junit5params)
        androidTestRuntimeOnly(junit5engine)
        androidTestImplementation(androidTestRunner)
    }

    fun DependencyHandlerScope.androidFrameworkTests() {
        junit5()
        androidTestImplementation(extJunit)
        androidTestImplementation(robolectric)
    }

    fun DependencyHandlerScope.androidMockTests() {
        junit5android()
        testImplementation(extJunit)
        testImplementation(mockito)
    }

    fun DependencyHandlerScope.androidUiTests() {
        junit5android()
        androidTestImplementation(extJunit)
        androidTestImplementation(espresso)
    }
}