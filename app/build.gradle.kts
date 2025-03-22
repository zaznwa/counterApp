plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    //KSP must be declared at the bottom of hilt
    // Ksp plugin
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.geeks.mvp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.geeks.mvp"
        minSdk = 28
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
        viewBinding = true
    }

}

dependencies {
    //Live Data & View Model
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //navigation fragment
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //koin
    implementation(libs.koin.android)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)


    implementation(libs.converter.gson) // Для Gson
    // implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion") // Для Moshi

    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.glide)

    implementation (libs.androidx.lifecycle.extensions)



}
