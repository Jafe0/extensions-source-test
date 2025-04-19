plugins {
    id("tachiyomi.extension")
}

dependencies {
    implementation(project(":lib:synchrony"))
    implementation(libs.jsoup)
    implementation(libs.okhttp)
}
