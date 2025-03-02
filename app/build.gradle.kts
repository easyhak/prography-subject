plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
}
android {
    namespace = "com.example.prographysubject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.prographysubject"
        minSdk = 26
        targetSdk = 35
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.googleid)
    implementation(libs.androidx.compose.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // google fonts
    implementation(libs.androidx.ui.text.google.fonts)

    // material icon
    api(libs.androidx.material.icons)
    api(libs.androidx.material.icons.android)

    // hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.core)

    // hilt navigation compose
    implementation(libs.androidx.hilt.navigation.compose)

    // navigation compose
    implementation(libs.androidx.navigation.compose)

    // serialization
    implementation(libs.kotlinx.serialization.json.jvm)

    // paging3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    // coil
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.compose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter) // serialization converter
    implementation(libs.logging.interceptor)

    // datastore
    implementation(libs.androidx.datastore.preferences)

    // shimmer
    implementation(libs.compose.shimmer)

}
