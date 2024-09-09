plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.example.hh_clone_effective_test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hh_clone_effective_test"
        minSdk = 28
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(project(":details:di"))

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Koin
    implementation(libs.koin.android)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //Core
    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":search:ui"))
    implementation(project(":search:di"))
    implementation(project(":login:ui"))
    implementation(project(":details:ui"))
    implementation(project(":favorites:ui"))
    implementation(project(":favorites:di"))
    implementation(project(":profile:ui"))
    implementation(project(":replies:ui"))
    implementation(project(":messages:ui"))

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx.v260)


}