plugins {
    id("tachiyomi.extension")
}

dependencies {
    // Dependências específicas da sua extensão
    implementation(project(":lib:synchrony"))
    
    // Se no futuro precisar de mais libs, pode adicionar aqui
    // exemplo:
    // implementation(project(":lib:some-other-lib"))
}
