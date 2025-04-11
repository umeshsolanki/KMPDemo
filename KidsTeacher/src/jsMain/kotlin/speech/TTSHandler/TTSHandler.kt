package speech.TTSHandler

import kotlinx.browser.window

enum class Lang(val code: String) {
    ENGLISH("en-IN"), HINDI("hi-IN")
}

external class SpeechSynthesisUtterance(text: String) {
    var lang: String
    var pitch: Float
    var rate: Float
    var text: String
}

fun getDefaultUtterance(text: String): SpeechSynthesisUtterance {
    val utterance = SpeechSynthesisUtterance(text)
    utterance.lang = Lang.ENGLISH.code
    utterance.pitch = 0.7f
    utterance.rate = 0.5f
    return utterance
}

object TTSHandler {

    val synth = window.asDynamic().speechSynthesis

    fun speak(text: String, lang: Lang = Lang.ENGLISH) {
        val toSpeak = getDefaultUtterance(text)
        toSpeak.lang = lang.code
        synth.speak(toSpeak)
    }


    fun speakNow(text: String, lang: Lang = Lang.ENGLISH) {
        if (synth.speaking) {
            synth.cancel()
        }
        val toSpeak = getDefaultUtterance(text)
        toSpeak.lang = lang.code
        synth.speak(toSpeak)
    }

    fun pause() {
        synth.pause()
    }

    fun play() {
        synth.resume()
    }

    fun cancel() {
        synth.cancel()
    }


}