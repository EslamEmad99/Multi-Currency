/**
 * Using a version catalog allows us to centralize dependency management.
 * This makes it easier to update dependency versions in one place, ensuring consistency across the project.
 * It also improves readability and maintainability of our build scripts.
 * **/
buildscript {
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.kotlin.allopen)
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlinJVM) apply false
}