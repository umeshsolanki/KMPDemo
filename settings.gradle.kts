plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "KMPDemo"
include("KidsTeacher")
include("Common")
include("Hindi")
include("English")
include("Maths")
include("TTS")
