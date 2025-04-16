@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.distribution)
}

group = "com.devuss"
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
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
                implementation(libs.kotlin.stdlib)
                implementation(libs.coroutine.core)
                implementation(libs.ktor.client.core)
                implementation(libs.kotlinx.html)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.ktor.client.websockets)
                implementation(libs.ktor.client.logging)
            }
        }

        jsMain {
            dependencies {
                implementation(libs.kotlinx.html)
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlin.css)
                implementation(libs.coroutine.core)
                implementation(libs.ktor.clientjs)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.bundles.ktorserverbundle)
                //                implementation("org.slf4j:slf4j-log4j12:1.7.36")
//
                implementation(libs.ktor.client.encoding)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.cio)
                implementation(libs.jsoup)
                implementation(libs.ktor.serialization.gson)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.slf4j)
                implementation(libs.coroutine.core)
                implementation(libs.kotlin.css)
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
