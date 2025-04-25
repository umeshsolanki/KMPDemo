package player.howler

fun HowlOptions(): HowlOptions = js("{}")

enum class HowlerEvent(val value: String) {
    LOAD("load"), PLAY("play"), PAUSE("pause"), STOP("stop"), END("end"), LOAD_ERROR("loaderror"), PLAY_ERROR("playerror")
}
