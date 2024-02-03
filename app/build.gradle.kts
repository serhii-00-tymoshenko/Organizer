plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.serhiitymoshenko.organizer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.serhiitymoshenko.organizer"
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
            buildConfigField("String", "WEB_CLIENT_ID", project.properties["WEB_CLIENT_ID"].toString())
        }
        debug {
            buildConfigField("String", "WEB_CLIENT_ID", project.properties["WEB_CLIENT_ID"].toString())
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // ViewModel lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Firebase cloud messaging
    implementation("com.google.firebase:firebase-messaging-ktx")

    // Firebase firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Firebase auth
    implementation("com.google.firebase:firebase-auth-ktx")

    // Koin
    implementation("io.insert-koin:koin-android:3.5.3")

    // Coil
    implementation("io.coil-kt:coil:2.5.0")

    // Google Play services
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
}

kapt {
    correctErrorTypes = true
}