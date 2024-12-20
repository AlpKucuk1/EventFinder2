buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.1") // Ensure this matches your Android Gradle Plugin version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.4")
        classpath("com.google.gms:google-services:4.4.1") // For Firebase
    }
}

plugins {
    kotlin("jvm") version "1.8.10" apply false
}
