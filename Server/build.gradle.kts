plugins {
    alias(libs.plugins.kotlinJvm)
//    alias(libs.plugins.ktor)
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
    implementation(projects.hindi)
    implementation(projects.kidsTeacher)
    implementation(projects.maths)
    implementation(projects.dbCore)
    implementation(libs.bundles.dbundle)
    implementation(libs.bundles.ktorserverbundle)
    implementation(libs.gson)
}

tasks.test {
    useJUnitPlatform()
}