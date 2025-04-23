package kidsTeacher.english

import com.bootstrap.colors.Color
import com.bootstrap.colors.ThemedColor
import com.bootstrap.colors.backgroundColor
import com.bootstrap.colors.textColor
import com.bootstrap.components.cards.Card
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.spaced
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.applyModifier
import com.bootstrap.modifier.button
import com.bootstrap.modifier.classes
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseOverFunction
import kotlinx.html.p
import speech.tts.Lang
import speech.tts.TTSHandler
import util.setContent

fun main() {
    window.onload = {
        println("WordMeaningAppUi initialized")
        WordMeaningAppUi().render()
    }
}


class WordMeaningAppUi {
    val scope = MainScope()
    val viewModel = WordMeaningViewModel()

    fun render() {
        setContent("en-words") {
            Row {
                Column(3, modifier = Modifier.spaced(all = 2)) {
                    Card(
                        title = "Words in English and Hindi", modifier = Modifier.backgroundColor(Color.WHITE)
                    ) {
                        id = "groups"
                        +"Loading..."
                    }
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

    fun observeGroups() {
        scope.launch {
            viewModel.groups.collect { groups ->
                setContent("groups") {
                    groups.forEachIndexed { index, uiData ->
                        p(Modifier.textColor(ThemedColor.SECONDARY).classes) {
                            applyModifier(Modifier.button())
                            onClickFunction = {
                                viewModel.loadWords(uiData)
                            }
                            onMouseOverFunction = {
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
                        h1 {
                            id = "category"
                            onClickFunction = {
                                TTSHandler.speak(category)
                            }
                            +category
                        }
                        words.forEach { word ->
                            Column(3, modifier = Modifier.button().spaced(2)) {
                                Card(modifier = Modifier.backgroundColor(ThemedColor.INFO)) {
                                    Row {
                                        Column {
                                            onClickFunction = {
                                                TTSHandler.speakNow(word.en.orEmpty())
                                            }
                                            p(Modifier.textColor(ThemedColor.SUCCESS).classes) {
                                                +word.en.orEmpty()
                                            }
                                        }
                                        Column {
                                            onClickFunction = {
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