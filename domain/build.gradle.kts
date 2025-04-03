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
    testImplementation(libs.junit.junit)
    testImplementation(libs.truth)
}