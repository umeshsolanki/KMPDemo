package kidsTeacher.maths.ui

import com.bootstrap.colors.ThemedColor
import com.bootstrap.colors.backgroundColor
import com.bootstrap.colors.textColor
import com.bootstrap.components.cards.Card
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.spaced
import com.bootstrap.dimens.Height
import com.bootstrap.dimens.height
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.classes
import kidsTeacher.maths.NumberSystemViewModel
import kotlinx.browser.window
import kotlinx.html.h1
import kotlinx.html.js.onClickFunction
import logger.jsLog
import speech.tts.Lang
import speech.tts.TTSHandler
import tts.setToggleTTSEvents
import util.setContent

fun main() {
    window.onload = {
        NumbersPageUi().renderUi()
        setToggleTTSEvents()
    }
}

class NumbersPageUi {
    val viewModel = NumberSystemViewModel()

    fun renderUi() {
        setContent("number-system-content") {
            Row {
                Column(3, modifier = Modifier.spaced(all = 2)) {
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
                        viewModel.data.numbers.forEach { (key, value) ->
                            Column(4, modifier = Modifier.spaced(all = 2)) {
                                Card(modifier = Modifier.backgroundColor(ThemedColor.WARNING)) {
                                    Row {
                                        Column {
                                            h1(Modifier.textColor(ThemedColor.INFO).classes) {
                                                +key
                                                onClickFunction = {
                                                    jsLog("Clicked on $key")
                                                    val spelling = value.en.toCharArray().joinToString(" ")
                                                    TTSHandler.speakNow("$spelling - $key")
                                                }
                                            }
                                        }
                                        Column {
                                            h1(Modifier.textColor(ThemedColor.SUCCESS).classes) {
                                                +value.hi
                                                onClickFunction = {
                                                    jsLog("Clicked on $key")
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