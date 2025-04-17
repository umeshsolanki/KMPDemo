@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.distribution)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
//    alias(libs.plugins.ktor)
    alias(libs.plugins.ksp)
}

group = "com.devuss"
version = "1.0-SNAPSHOT"

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

    }
    sourceSets {

        commonMain {

            dependencies {
                implementation(libs.bundles.commonMain)
                implementation(projects.common)
                implementation(projects.tts)
                implementation(libs.kotlinx.html)
                implementation(libs.kotlinx.json.serialization)
            }
        }

        jsMain {
            dependencies {
                implementation(projects.common)
                implementation(projects.tts)
                implementation(libs.bundles.jsMain)
                implementation(libs.kotlinx.html)
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.457")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
            }
        }
        jvmMain {

            dependencies {
                implementation(projects.common)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.bundles.ktorserverbundle)
                implementation(libs.bundles.jvmMain)
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