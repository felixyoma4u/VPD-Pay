plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    alias(libs.plugins.kapt)
}




android {
    namespace = "com.example.vpdpay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vpdpay"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        // Used for Unit testing Android dependent elements in /test folder
        unitTests.isIncludeAndroidResources  = true
        unitTests.isReturnDefaultValues = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)

    //Room
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)



}