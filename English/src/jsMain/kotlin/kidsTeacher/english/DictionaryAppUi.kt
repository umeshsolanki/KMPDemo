package kidsTeacher.english

import com.bootstrap.colors.ThemedColor
import com.bootstrap.colors.backgroundColor
import com.bootstrap.colors.textColor
import com.bootstrap.components.button.Button
import com.bootstrap.components.cards.Card
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.spaced
import com.bootstrap.modifier.*
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseOverFunction
import kotlinx.html.p
import logger.jsLog
import org.w3c.dom.COMPLETE
import org.w3c.dom.DocumentReadyState
import org.w3c.dom.HTMLButtonElement
import speech.tts.Lang
import speech.tts.TTSHandler
import tts.setToggleTTSEvents
import util.setContent

fun main() {
    window.onload = {
        jsLog("WordMeaningAppUi initialized")
        DictionaryAppUi().render()
    }
    document.onreadystatechange = {
        if (document.readyState == DocumentReadyState.COMPLETE) {
            jsLog("Document is ready")
            setToggleTTSEvents()
        }
    }
}


class DictionaryAppUi {
    val scope = MainScope()
    val viewModel = DictionaryViewModel()
    var isPlaying = false

    fun render() {
        setContent("en-words") {
            Row {
                Column(3, modifier = Modifier.spaced(all = 2)) {
                    id = "groups"
                    +"Loading..."
                }
                Column(9) {
                    id = "words"
                }
            }
        }
        observeData()
    }

    fun observeData() {
        observeGroups()
        observeWords()
    }

//    fun queueTTSWords(word: String, lang: Lang = Lang.ENGLISH) {
//        TTSHandler.speakNow(word)
//    }

    fun observeGroups() {
        scope.launch {
            viewModel.groups.collect { groups ->
                setContent("groups") {
                    groups.forEachIndexed { index, uiData ->
                        p(Modifier.padding(0).textColor(ThemedColor.SECONDARY).classes) {
                            applyModifier(Modifier.button())
                            onClickFunction = {
                                viewModel.loadWords(uiData)
                            }
                            onMouseOverFunction = {
                                jsLog("Mouse over group: ${uiData.en}")
                                TTSHandler.speakNow(uiData.en.orEmpty())
                            }
                            +uiData.en.orEmpty()
                        }
                    }
                }
            }
        }
    }

    private fun observeWords() {
        scope.launch {
            viewModel.words.collect { words ->
                setContent("words") {
                    val category = words.firstOrNull()?.group.orEmpty()
                    Row {
                        Column(12) {
                            Row {
                                Column(6) {
                                    h1 {
                                        id = "category"
                                        onClickFunction = {
                                            TTSHandler.speak(category)
                                        }
                                        +category
                                    }
                                }
                                Column(6) {
                                    Button {
                                        +"Play All"
                                        onClickFunction = {
                                            if (isPlaying) {
                                                TTSHandler.cancel()
                                            }
                                            isPlaying = isPlaying.not()
                                            if (isPlaying) {
                                                (it.target as HTMLButtonElement).innerHTML = "Stop"
                                                words.forEach { word ->
                                                    val spelling = word.en?.toCharArray()?.joinToString(" ")
                                                    TTSHandler.speak(
                                                        spelling.plus(" ").plus(word.en.orEmpty()).plus(" means ")
                                                    )
                                                    TTSHandler.speak(word.hi.orEmpty(), Lang.HINDI)
                                                }
                                            } else {
                                                (it.target as HTMLButtonElement).innerText = "Play All"
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        words.forEach { word ->
                            Column(3, modifier = Modifier.button().spaced(2)) {
                                Card(modifier = Modifier.backgroundColor(ThemedColor.INFO)) {
                                    Row {
                                        Column {
                                            onClickFunction = {
                                                jsLog("Clicked on ${word.en}")
                                                TTSHandler.speakNow(word.en.orEmpty())
                                            }
                                            p(Modifier.textColor(ThemedColor.SUCCESS).classes) {
                                                +word.en.orEmpty()
                                            }
                                        }
                                        Column {
                                            onClickFunction = {
                                                jsLog("Clicked on ${word.hi}")
                                                TTSHandler.speakNow(word.hi.orEmpty(), Lang.HINDI)
                                            }
                                            p(Modifier.textColor(ThemedColor.PRIMARY).classes) {
                                                +word.hi.orEmpty()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}