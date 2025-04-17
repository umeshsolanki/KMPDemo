@file:OptIn(ExperimentalKotlinGradlePluginApi::class)
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.distribution)
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
                api(projects.kidsTeacher)
                implementation(projects.hindi)
                implementation(projects.english)
                implementation(projects.maths)
                implementation(projects.tts)

                implementation(libs.kotlinx.html)
                implementation(libs.kotlinx.json.serialization)

            }
        }


        jsMain {
            dependencies {
                implementation(projects.common)
                api(projects.kidsTeacher)
                implementation(projects.hindi)
                implementation(projects.english)
                implementation(projects.maths)
                implementation(projects.tts)

//                implementation(libs.kotlinx.html)
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.457")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")

            }
        }
        jvmMain {
            dependencies {
//                implementation("org.mongodb:bson-kotlinx:${mongodb_driver}")
//                implementation("org.litote.kmongo:kmongo:4.8.0")

//                implementation("com.squareup.okio:okio:3.1.0")
                implementation(libs.mongo.driversync)
                implementation(libs.mongo.coroutine)
                implementation(libs.mongo.bson.kotlin)

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
