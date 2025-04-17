package kidsTeacher.english

import com.bootstrap.colors.Color
import com.bootstrap.colors.ThemedColor
import com.bootstrap.colors.backgroundColor
import com.bootstrap.colors.textColor
import com.bootstrap.components.cards.Card
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.spaced
import com.bootstrap.modifier.*
import data.wordsData
import kotlinx.browser.window
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseOverFunction
import kotlinx.html.p
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import speech.tts.Lang
import speech.tts.TTSHandler
import util.setContent

fun main() {
    WordMeaningViewModel()
}

@Serializable
data class WordMeaningDictionary(
    val data: List<WordCategory>
)

@Serializable
data class WordCategory(
    val type: String, val words: List<WordMeaning>
)

@Serializable
data class WordMeaning(
    val en: String, val hi: String
)


class WordMeaningViewModel {

    private val data = Json.decodeFromString<WordMeaningDictionary>(wordsData)

    init {
        println("WordMeaningViewModel initialized")
        window.onload = {
            setContent("en-words") {
                Row {
                    Column(3, modifier = Modifier.spaced(all = 2)) {
                        Card(
                            title = "Words in English and Hindi", modifier = Modifier.backgroundColor(Color.WHITE)
                        ) {
                            Row {
                                data.data.forEachIndexed { index, category ->
                                    Column(6) {
                                        p(Modifier.textColor(ThemedColor.SECONDARY).classes) {
                                            applyModifier(Modifier.button())
                                            onClickFunction = {
                                                loadWords(index)
                                            }
                                            onMouseOverFunction = {
                                                TTSHandler.speakNow(category.type)
                                            }
                                            +category.type
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column(9) {
                        id = "words"

                    }
                }
            }
        }
    }

    private fun loadWords(type: Int) {
        setContent("words") {
            val category = data.data[type]
            category.type
            Row {
                h1 {
                    id = "category"
                    onClickFunction = {
                        TTSHandler.speak(category.type)
                    }
                    +category.type
                }
                category.words.forEach { word ->
                    Column(3, modifier = Modifier.button().spaced(2)) {
                        Card(modifier = Modifier.backgroundColor(ThemedColor.INFO)) {
                            Row {
                                Column {
                                    onClickFunction = {
                                        TTSHandler.speakNow(word.en)
                                    }
                                    p(Modifier.textColor(ThemedColor.SUCCESS).classes) {
                                        +word.en
                                    }
                                }
                                Column {
                                    onClickFunction = {
                                        TTSHandler.speakNow(word.hi, Lang.HINDI)
                                    }
                                    p(Modifier.textColor(ThemedColor.PRIMARY).classes) {
                                        +word.hi
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