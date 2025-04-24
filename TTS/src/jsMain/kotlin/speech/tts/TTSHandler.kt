package speech.tts

import kotlinx.browser.window
import logger.jsLog
import tts.isTTSEnabled

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

    init {
        jsLog("TTSHandler initialized")
    }

    val synth = window.asDynamic().speechSynthesis

    fun speak(text: String, lang: Lang = Lang.ENGLISH) {
        jsLog("Speak called with text: $text and lang: ${lang.code}")
        if (!isTTSEnabled()) {
            jsLog("TTS is not enabled")
            return
        }
        val toSpeak = getDefaultUtterance(text)
        toSpeak.lang = lang.code
        synth.speak(toSpeak)
    }


    fun speakNow(text: String, lang: Lang = Lang.ENGLISH) {
        if (!isTTSEnabled()) {
            return
        }
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