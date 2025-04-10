package crawler

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

val client = HttpClient(Js) {
    install(WebSockets) {
        pingInterval = 15.seconds
    }
}


object CrawlerWebsocket {

    val wsFramesToSend = MutableStateFlow<Frame?>(null)
    val wsReceivedFrame = MutableStateFlow<Frame.Text?>(null)


    fun stop() {
        println("Stopping Crawler Websocket")
        client.close()
    }

    suspend fun connect() {
        println("Starting Crawler Websocket")
        client.webSocket(method = HttpMethod.Get, host = "localhost", port = 8080, path = "/crawler-ws") {
            println("Connected to ws")
            launch {
                wsFramesToSend.collect {
                    println("Sending frame: $it")
                    it?.let { it1 -> send(it1) }
                }
            }
            while (true) {
                val othersMessage = incoming.receive() as? Frame.Text
                othersMessage?.let {
                    println("Received frame: $it")
                    wsReceivedFrame.value = it
                }
            }
        }
    }

}