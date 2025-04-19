plugins {
    id("tachiyomi.extension")
}

dependencies {
    implementation(project(":lib:synchrony"))

    // bibliotecas padrões de extensões
    implementation(libs.jsoup)
    implementation(libs.okhttp)
}
