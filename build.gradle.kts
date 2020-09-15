plugins {
    id("org.jetbrains.kotlin.js") version "1.4.10"
}

group = "com.er453r.ca"
version = "0.1"

repositories {
    jcenter()
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains:kotlin-css:1.0.0-pre.114-kotlin-1.4.0")
}

kotlin {
    js {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }
}
