package org.hitvaani.home

import com.bootstrap.components.layout.Row
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script
import common.addBootstrapAndCommon
import org.hitvaani.pagecrawler.IPageContentEvent
import org.hitvaani.pagecrawler.SiteCrawler
import org.hitvaani.pagecrawler.getMessage

class CrawlerController(routing: Routing) {

    private val scope = CoroutineScope(Dispatchers.IO)

    var id = 0

    private val wsConnections = mutableMapOf<DefaultWebSocketSession, Int>()

    init {
        init(routing)
        observePageContent()
    }

    private fun observePageContent() {
        scope.launch {
            println("Observing page content to broadcast")
            SiteCrawler.pageContent.collect {
                launch {
                    it?.let { it1 -> broadcastPageContent(it1) }
                }
            }
        }
    }

    private suspend fun broadcastPageContent(content: IPageContentEvent) {
        println("Broadcasting page content")
        val message = content.getMessage()
        wsConnections.keys.forEach { session ->
            if (session.isActive) {
                println("Broadcasting to session: $session, $message")
                session.send(message)
            }
        }
    }

    private fun init(routing: Routing) {
        routing.apply {
            websocket()
            get("/crawler") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/WebVaaniPath.js") {}
                        }
                    }
                    body {
                        Row {
                            id = "HomePageBody"
                        }
                    }
                }
            }
        }
    }

    private fun Routing.websocket() {
        webSocket("/crawler-ws") {
            wsConnections[this] = ++id
            send(Frame.Text("Submit url to crawl"))
            for (frame in incoming) {
                println("Received frame: $frame")
                delay(100)
                when (frame) {
                    is Frame.Text -> {
                        val url = frame.readText()
                        SiteCrawler.submitUrl(url)
                        send(Frame.Text("Crawling: $url"))
                    }

                    else -> {
                        delay(100)
                        continue
                    }
                }
            }
            send(Frame.Text("Bye!"))
            println("Connection closed")
            wsConnections.remove(this)
        }
    }

}
