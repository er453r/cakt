plugins {
    id("org.jetbrains.kotlin.js") version "1.4.21"
    id("com.github.ben-manes.versions") version "0.36.0"
}

group = "com.er453r.ca"
version = "0.1"

repositories {
    jcenter()
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains:kotlin-css:1.0.0-pre.139-kotlin-1.4.21")
}

kotlin {
    js {
        browser()
    }
}
