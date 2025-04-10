import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    id ("kotlin-parcelize")
    id ("com.google.devtools.ksp")
    alias(libs.plugins.kover.coverage)
}

android {
    namespace = "com.avsoftware.dashboard"
    compileSdk = Configs.compileSdk

    // Load properties from ~/.gradle/gradle.properties
    val gradleUserHome = System.getProperty("user.home") + "/.gradle/gradle.properties"
    val properties = Properties()
    if (file(gradleUserHome).exists()) {
        FileInputStream(gradleUserHome).use { input ->
            properties.load(input)
        }
    }
    val fmpApiKey = properties.getProperty("fmpApiKey") ?: "default_key"

    defaultConfig {
        applicationId = "com.avsoftware.dashboard"
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
        }
    }
    flavorDimensions.add("environment") // Renamed for clarity

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev" // Optional: com.avsoftware.dashboard.dev
            buildConfigField("boolean", "DEVELOPER_OPTIONS_ENABLED", "true")
            buildConfigField("String", "FMP_API_KEY", "\"$fmpApiKey\"")
            buildConfigField("String", "FMP_BASE_URL", value="\"https://financialmodelingprep.com/\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("boolean", "DEVELOPER_OPTIONS_ENABLED", "false")
            buildConfigField("String", "FMP_API_KEY", "\"$fmpApiKey\"")
            buildConfigField("String", "FMP_BASE_URL", value="\"https://financialmodelingprep.com/\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion // Match your Kotlin version
    }
    buildToolsVersion = Configs.buildToolsVersion
}

dependencies {

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":database"))
    implementation(project(":domain"))
    implementation(project(":core-ui"))
    implementation(project(":graphing"))

    // compose BOM
    implementation(platform(libs.androidx.compose.bom))
    // Add the entire compose bundle
    implementation(libs.bundles.compose)
    // Since ui-tooling is typically used in debug builds, keep it separate
    debugImplementation(libs.androidx.ui.tooling)

    // timber
    implementation(libs.timber)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OKHttp
    implementation(libs.okhttp)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Orbit MVI
    implementation(libs.orbit.compose)
    implementation(libs.orbit.core)
    implementation(libs.orbit.viewmodel)
    testImplementation(libs.orbit.test)

    // Orbit MVI Framework
    implementation(libs.orbit.core)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.compose)
    testImplementation(libs.orbit.test)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    implementation(libs.navigation.compose)
    implementation(libs.material.icons.extended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // test coverage
    // test coverage report include modules
    kover(project(":graphing"))
    kover(project(":data"))
    kover(project(":core"))
    kover(project(":core-ui"))
    kover(project(":domain"))
    kover(project(":data"))
    kover(project(":data-fake"))
    kover(project(":database"))
}