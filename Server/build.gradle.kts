plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.application)
}

group = "com.devuss"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.hitvaani.MainKt")
    applicationDefaultJvmArgs = listOf("")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.hitvaani.MainKt"
    }
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