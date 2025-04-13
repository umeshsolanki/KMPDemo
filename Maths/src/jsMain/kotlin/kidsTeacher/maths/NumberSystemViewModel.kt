package kidsTeacher.maths

import com.bootstrap.colors.ThemedColor
import com.bootstrap.colors.backgroundColor
import com.bootstrap.colors.hexBgColor
import com.bootstrap.colors.textColor
import com.bootstrap.components.cards.Card
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.spaced
import com.bootstrap.dimens.Height
import com.bootstrap.dimens.height
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.classes
import kotlinx.browser.window
import kotlinx.html.h1
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import speech.tts.Lang
import speech.tts.TTSHandler
import util.setContent

fun main() {
    NumberSystemViewModel()
}

val numberData: String = """
{"numbers":{
  "1": { "en": "one", "hi": "एक" },
  "2": { "en": "two", "hi": "दो" },
  "3": { "en": "three", "hi": "तीन" },
  "4": { "en": "four", "hi": "चार" },
  "5": { "en": "five", "hi": "पांच" },
  "6": { "en": "six", "hi": "छह" },
  "7": { "en": "seven", "hi": "सात" },
  "8": { "en": "eight", "hi": "आठ" },
  "9": { "en": "nine", "hi": "नौ" },
  "10": { "en": "ten", "hi": "दस" },
  "11": { "en": "eleven", "hi": "ग्यारह" },
  "12": { "en": "twelve", "hi": "बारह" },
  "13": { "en": "thirteen", "hi": "तेरह" },
  "14": { "en": "fourteen", "hi": "चौदह" },
  "15": { "en": "fifteen", "hi": "पंद्रह" },
  "16": { "en": "sixteen", "hi": "सोलह" },
  "17": { "en": "seventeen", "hi": "सत्रह" },
  "18": { "en": "eighteen", "hi": "अठारह" },
  "19": { "en": "nineteen", "hi": "उन्नीस" },
  "20": { "en": "twenty", "hi": "बीस" }
}
}
"""

@Serializable
data class NumberDictionary(
    val numbers: Map<String, NumberData>
)

@Serializable
data class NumberData(
    val en: String, val hi: String
)

class NumberSystemViewModel {
    init {
        println("KidsTeacherViewModel initialized")
        window.onload = {
            val data = Json.decodeFromString<NumberDictionary>(numberData)
            setContent("number-system-content") {
                Row {
                    Column(3, modifier = Modifier.hexBgColor("#77FF33").spaced(all = 2)) {
                        Card(
                            title = "Numbers from 1 to 10, in English",
                            modifier = Modifier.backgroundColor(ThemedColor.INFO)
                        ) {
                            h1(Modifier.textColor(ThemedColor.SUCCESS).classes) {
                                +"Number System"
                            }
                            Row(modifier = Modifier.height(Height.H_100)) {
                                (1..10).forEach { i ->
                                    Column(6) {
                                        h1(Modifier.textColor(ThemedColor.WARNING).classes) {
                                            +"$i"
                                        }
                                    }
                                }
                                (11..20).forEach { i ->
                                    Column(6) {
                                        h1(Modifier.textColor(ThemedColor.WARNING).classes) {
                                            +"$i"
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column(9) {
                        Row {
                            data.numbers.forEach { (key, value) ->
                                Column(4, modifier = Modifier.spaced(all = 2)) {
                                    Card(modifier = Modifier.backgroundColor(ThemedColor.WARNING)) {
                                        Row {
                                            Column {
                                                h1(Modifier.textColor(ThemedColor.INFO).classes) {
                                                    +key
                                                    onClickFunction = {
                                                        println("Clicked on $key")
                                                        val spelling = value.en.toCharArray().joinToString(" ")
                                                        TTSHandler.speakNow("$spelling - $key")
                                                    }
                                                }
                                            }
                                            Column {
                                                h1(Modifier.textColor(ThemedColor.SUCCESS).classes) {
                                                    +value.hi
                                                    onClickFunction = {
                                                        println("Clicked on $key")
                                                        TTSHandler.speakNow(value.hi, Lang.HINDI)
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
    }
}
