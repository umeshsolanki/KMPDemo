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
                implementation(projects.common)
                implementation(libs.kotlinx.html)
                implementation(libs.kotlinx.json.serialization)
                implementation(libs.bundles.commonMain)
            }
        }


        jsMain {
            dependencies {
                implementation(projects.common)
                implementation(libs.bundles.jsMain)
                implementation(npm("howler", "2.2.4"))

//                implementation(libs.kotlinx.html)
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.457")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")

            }
        }
        jvmMain {

            dependencies {
                implementation(libs.bundles.jvmMain)
                implementation(libs.bundles.ktorserverbundle)
                implementation(projects.common)
                implementation(libs.kotlinx.json.serialization)
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