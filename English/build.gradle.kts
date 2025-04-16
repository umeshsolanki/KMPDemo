@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
//    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.distribution)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

group = "com.devuss"
version = "1.0-SNAPSHOT"

kotlin {

    jvm {
//        withJava()
//        application {
//
//        }
        binaries {
            executable {
                mainClass.set("org.hitvaani.MainKt")
            }

        }
    }
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {}
        }

    }
    sourceSets {

        commonMain {
            dependencies {
                implementation(projects.common)
                implementation(libs.bundles.ktorclientbundle)
                implementation(libs.kotlinx.html)
                implementation(libs.ktor.client.encoding)
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.coroutine.core)
                implementation(libs.ktor.client.core)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.ktor.client.websockets)
                implementation(libs.ktor.client.logging)
            }
        }


        jsMain {
            dependencies {
                implementation(projects.common)
                implementation(projects.tts)
                implementation(libs.kotlinx.html)
                implementation(libs.kotlin.css)
                implementation(libs.ktor.clientjs)
                implementation(libs.coroutine.core)
            }
        }
        jvmMain {
            dependencies {
                implementation(projects.common)
                implementation(projects.tts)
                implementation(libs.bundles.ktorserverbundle)
                implementation(libs.ktor.client.encoding)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.server.cors)
                implementation(libs.jsoup)
                implementation(libs.ktor.serialization.gson)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.slf4j)
                implementation(libs.coroutine.core)
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