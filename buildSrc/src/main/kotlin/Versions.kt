object Versions {

    const val gradle = "4.1.0"
    const val kotlin = "1.4.32"

    const val material = "1.2.1"
    object androidx {
        const val coreKtx = "1.3.2"
        const val navigation = "2.3.5"
        const val constraintLayout = "2.0.4"
        const val appcompat = "1.2.0"
        const val lifecycle = "2.3.1"
        const val room = "2.3.0"
    }

    const val junit4 = "4.12"
    const val junit5 = "5.7.0"
    const val extJunit = "1.1.2"
    const val espresso = "3.3.0"
    const val robolectric = "1.0.0"
    const val mockito = "1.10.19"
    const val androidTestRunner = "1.2.0"

    val String.snapshot get() = "$this-SNAPSHOT"
}