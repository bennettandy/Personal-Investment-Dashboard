plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.avsoftware.developer_utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":domain"))

    // compose BOM
    implementation(platform(libs.androidx.compose.bom))
    // Add the entire compose bundle
    implementation(libs.bundles.compose)
    // Since ui-tooling is typically used in debug builds, keep it separate
    debugImplementation(libs.androidx.ui.tooling)

    // Compose Charts
    implementation (libs.compose.charts)

    // lottie animations
    implementation (libs.lottie.compose)

    implementation(platform(libs.androidx.compose.bom))

    // Add the entire compose bundle
    implementation(libs.bundles.compose)
    implementation(libs.androidx.ui.text.google.fonts)

    // Since ui-tooling is typically used in debug builds, keep it separate
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.timber)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}