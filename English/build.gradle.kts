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
                implementation(libs.bundles.commonMain)
//                implementation(libs.exposed.core)
//                implementation(libs.exposed.dao)
//                implementation(libs.exposed.kotlin.datetime)
            }
        }


        jsMain {
            dependencies {
                implementation(projects.common)
                implementation(projects.tts)
                implementation(libs.bundles.jsMain)
            }
        }
        jvmMain {
            dependencies {
                implementation(projects.common)
                implementation(projects.tts)
                api(projects.dbCore)
                implementation(libs.bundles.allDb)
                implementation(libs.bundles.ktorserverbundle)
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