plugins {
    kotlin("js") version "1.4-M2"
}
group = "com.piu130"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
}
dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
    implementation("org.jetbrains:kotlin-react:16.13.0-pre.93-kotlin-1.4-M2")
    implementation("org.jetbrains:kotlin-react-dom:16.13.0-pre.93-kotlin-1.4-M2")
    implementation(npm("react","16.13.0"))
    implementation(npm("react-dom","16.13.0"))
}
kotlin {
    js {
        browser {}
        binaries.executable()
    }
}