plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.avsoftware.core_ui"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.sourceCompatibility
        targetCompatibility = Configs.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion // Match your Kotlin version
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    // Add the entire compose bundle
    implementation(libs.bundles.compose)
    implementation(libs.androidx.ui.text.google.fonts)
    // Since ui-tooling is typically used in debug builds, keep it separate
    debugImplementation(libs.androidx.ui.tooling)


    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // ui testing
    androidTestImplementation (libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}