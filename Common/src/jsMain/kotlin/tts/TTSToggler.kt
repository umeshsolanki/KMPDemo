package tts

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.*
import util.getElementById

/**
 * This function toggles the TTS (Text-to-Speech) button in the UI.
 * When the button is clicked, it changes its text and class to indicate whether TTS is enabled or disabled.
 */

fun main() {
//    println("TTSToggler main function")
    document.onreadystatechange = {
//        println("Document is ready")
        if (document.readyState == DocumentReadyState.COMPLETE) {
            println("TTSToggler initialized")
            setToggleTTSEvents()
        }
    }
}

fun setToggleTTSEvents() {
    println("Setting TTS toggle events")
    var isTTSOn = isTTSEnabled()
    val ttsToggle = getElementById<HTMLElement>("tts-toggle")
    updateTTSToggleButton(isTTSOn)
    ttsToggle?.addEventListener("click", {
        isTTSOn = !isTTSOn
        setTTSEnabled(isTTSOn)
        updateTTSToggleButton(isTTSOn)
    })
}

fun updateTTSToggleButton(isTTSOn: Boolean) {
    val ttsToggle = getElementById<HTMLElement>("tts-toggle")
    ttsToggle?.innerHTML = if (isTTSOn) {
        ttsToggle?.classList?.add("bi-volume-up")
        "TTS ON"
    } else {
        ttsToggle?.classList?.add("bi-volume-mute")
        "TTS OFF"
    }
}

fun setTTSEnabled(on: Boolean) {
    window.localStorage["tts"] = on.toString()
}

fun isTTSEnabled(): Boolean {
    val tts = window.localStorage["tts"]
    return tts?.toBoolean() ?: false
}