import org.gradle.api.JavaVersion

object Configs {
    const val minSdk = 26
    const val targetSdk = 35
    const val compileSdk = 35

    // Kotlin
    const val kotlinCompilerExtensionVersion = "1.4.8"

    //
    const val jvmTarget = "1.8"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
    const val coreLibraryDesugaringEnabled = true
}