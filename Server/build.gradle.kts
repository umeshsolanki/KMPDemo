plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.devuss"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.hitvaani.MainKt")
    applicationDefaultJvmArgs = listOf("")
}

dependencies {
    implementation(projects.common)
    implementation(projects.english)
    implementation(libs.ktor.server.core)
    implementation(libs.bundles.ktorserverbundle)
    implementation(libs.gson)
}

tasks.test {
    useJUnitPlatform()
}