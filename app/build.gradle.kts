import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.pixellock"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pixellock"
        minSdk = 28
        targetSdk = 36
        versionCode = 3
        versionName = "0.2.1"
    }
    // Release keystore lives outside the repo; without it the release build is simply unsigned.
    val signingProps = Properties().apply {
        val f = File(System.getProperty("user.home"), ".android-keystores/pixel7-lock.properties")
        if (f.exists()) f.inputStream().use { load(it) }
    }
    signingConfigs {
        if (signingProps.isNotEmpty()) {
            create("release") {
                storeFile = file(signingProps.getProperty("storeFile"))
                storePassword = signingProps.getProperty("storePassword")
                keyAlias = signingProps.getProperty("keyAlias")
                keyPassword = signingProps.getProperty("keyPassword")
            }
        }
    }
    buildTypes {
        release {
            signingConfig = android.signingConfigs.findByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}
