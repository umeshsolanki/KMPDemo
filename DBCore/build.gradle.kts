@file:OptIn(ExperimentalKotlinGradlePluginApi::class)
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    distribution
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktor)
    alias(libs.plugins.ksp)
}

group = "com.devuss"
version = "1.0-SNAPSHOT"


val ktor_version: String by project
val kotlin_version: String by project
val mongodb_driver: String by project
val kotlin_css_version: String by project
val kotlinx_serialization: String by project
val coroutine_version: String by project
val html_version: String by project
val sqllinVersion = "1.4.1"
val tensorflowVersion = "2.3.1"
val javaSigVersion = "0.3.0"

kotlin {

    jvm {
        binaries {
            executable {

            }
        }
    }
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {}
        }
    }    sourceSets {

        commonMain {


            dependencies {
                implementation(projects.common)
                api(project(":KidsTeacher"))
                implementation(project(":Hindi"))
                implementation(project(":English"))
                implementation(project(":Maths"))
                implementation(projects.tts)

                implementation("io.ktor:ktor-client-encoding:$ktor_version")
                implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${kotlinx_serialization}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation(libs.kotlinx.html)
                implementation(libs.kotlinx.json.serialization)
                implementation("io.ktor:ktor-client-websockets:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
            }
        }


        jsMain {
            dependencies {
                implementation(projects.common)
                api(project(":KidsTeacher"))
                implementation(project(":Hindi"))
                implementation(project(":English"))
                implementation(project(":Maths"))
                implementation(projects.tts)

//                implementation(libs.kotlinx.html)
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.457")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
                implementation("io.ktor:ktor-client-js:$ktor_version")

            }
        }
        jvmMain {
            dependencies {
                implementation(projects.common)
                implementation(project(":KidsTeacher"))
                implementation(project(":Hindi"))
                implementation(project(":English"))
                implementation(project(":Maths"))
                implementation(projects.tts)
                implementation("org.mongodb:mongodb-driver-kotlin-coroutine:$mongodb_driver")
                implementation("org.mongodb:bson-kotlinx:$mongodb_driver")
                implementation("io.ktor:ktor-server-core:$ktor_version")
                implementation("io.ktor:ktor-server-netty:$ktor_version")
                implementation("io.ktor:ktor-server-html-builder:$ktor_version")
                implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-server-websockets:${ktor_version}")
                implementation("io.ktor:ktor-server-double-receive:$ktor_version")
                implementation("org.slf4j:slf4j-log4j12:1.7.36")

                implementation("io.ktor:ktor-client-encoding:$ktor_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-server-cors:$ktor_version")

                implementation("org.jsoup:jsoup:1.19.1")

                implementation("io.ktor:ktor-serialization-gson:${ktor_version}")
                implementation(libs.kotlinx.json.serialization)
                implementation("org.slf4j:slf4j-simple:1.7.21")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:$kotlin_css_version")

                implementation("org.litote.kmongo:kmongo:4.8.0")


//                implementation("com.squareup.okio:okio:3.1.0")
//                implementation("org.mongodb:mongodb-driver-sync:4.8.1")
//                implementation("org.mongodb:bson:2.5.1")

            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
    tasks.register<Copy>("copyJsDistToResources") {
        duplicatesStrategy = DuplicatesStrategy.WARN
        from("$buildDir/dist/js/developmentExecutable")
        into("$projectDir/src/jvmMain/resources/js")
        include("*.js")
    }

    tasks.named("jsBrowserDevelopmentExecutableDistribution") {
        finalizedBy("copyJsDistToResources")
    }
    tasks.named("jsBrowserDistribution") {
        finalizedBy("copyJsDistToResources")
    }

}
