package com.hitvaani.homepage

import com.bootstrap.components.button.BUTTON
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.Row
import util.getElementById
import util.setContent
import crawler.CrawlerWebsocket
import io.ktor.websocket.*
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.js.onClickFunction
import kotlinx.html.p
import org.w3c.dom.HTMLInputElement

fun main() {
    HomePageUiHandler()
}

class HomePageUiHandler {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val messages = mutableListOf<String>()
    private val websocketHandler = CrawlerWebsocket

    init {
        println("HomePageUiHandler")
        window.onload = {
            loadUi()
            println("Home page loaded")
            scope.launch {
                launch {
                    websocketHandler.connect()
                }
                println("Connected to ws HomePageUiHandler")
                observeWsFrames()
            }
        }
    }

    private fun observeWsFrames() {
        scope.launch {
            println("Collecting frames")
            websocketHandler.wsReceivedFrame.collect {
                println("collected frame: $it")
                it ?: return@collect
                messages.add(it.readText())
                loadUi()
            }
        }
    }

    private fun sendUrl() {
        println("Button clicked")
        val url = getElementById<HTMLInputElement>("UrlInput")?.value as String
        scope.launch {
            websocketHandler.wsFramesToSend.value = Frame.Text(url)
        }
    }


    private fun loadUi() {
        setContent("HomePageBody") {
            Row {
                Column(4) {
                    input {
                        id = "UrlInput"
                        placeholder = "Enter URL"
                    }
                    BUTTON {
                        id = "SubmitButton"
                        onClickFunction = {
                            sendUrl()
                        }
                        +"Submit"
                    }
                }
                Column(8) {
                    messages.forEach {
                        p {
                            +it
                        }
                    }
                }
            }
        }
    }

}