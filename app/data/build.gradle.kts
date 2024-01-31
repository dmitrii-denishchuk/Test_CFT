plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "ru.iji.test_cft.data"
    compileSdk = 34

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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":app:domain"))
    implementation(libs.retrofit)
    implementation(libs.hilt.android)
    implementation(libs.moshi.kotlin)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.hilt.android.compiler)
    ksp(libs.room.compiler)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}