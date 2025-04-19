plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34
    namespace = "pt.thehentai"

    defaultConfig {
        minSdk = 21
    }
}

dependencies {
    implementation(project(":lib:synchrony"))
}
