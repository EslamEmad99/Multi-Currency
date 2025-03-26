/**
 * Using a version catalog allows us to centralize dependency management.
 * This makes it easier to update dependency versions in one place, ensuring consistency across the project.
 * It also improves readability and maintainability of our build scripts.
 * **/

/**
 * The domain model is implemented as a Java library rather than an Android library to keep it platform-agnostic.
 * This ensures that the domain logic remains independent of any specific Android framework dependencies,
 * making it easier to reuse the domain model in other projects or modules that may not be Android-specific.
 * Additionally, separating the domain model from Android-specific code improves testability and maintainability
 * by allowing unit tests to be run without the need for Android-specific components or instrumentation.
 * **/
plugins {
    id("java-library")
    alias(libs.plugins.kotlinJVM)
    id("kotlin-allopen")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

allOpen {
    annotation("com.eslam.domain.use_case.util.Mockable")
}

dependencies {
    // Coroutines for asynchronous
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // Moshi for remote models
    implementation(libs.moshi.kotlin)

    // dagger 2 for dependency injection
    implementation(libs.dagger)

    //Gson
    implementation(libs.gson)

    // testing
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.junit)
    implementation(libs.kotlin.stdlib)
}