plugins {
    kotlin("multiplatform") version "2.0.20"
}

group = "com.er453r.ca"
version = "0.1"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser()

        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            val kotlinxHtmlVersion = "0.11.0"

//            implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
            implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.798")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC.2")

        }
    }
}
