package org.hitvaani

import com.devuss.autoproxy.AutoProxyController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kidsTeacher.home.KidsLearningController
import kidsTeacher.numbers.NumberSystemController
import kotlinx.css.CssBuilder
import org.hitvaani.css.CssController
import org.hitvaani.home.CrawlerController
import org.hitvaani.home.HomePageController
import org.hitvaani.sewakvaani.SewakVaaniController
import kotlin.time.Duration.Companion.seconds

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(CORS) {
            anyHost()
        }
        install(WebSockets) {
            pingPeriod = 15.seconds
            timeout = 15.seconds
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }
        routing {
            staticResources("js", "/js")
            HomePageController(this)
            KidsLearningController(this)
            NumberSystemController(this)
            CrawlerController(this)
            AutoProxyController(this)
            CssController().init(this)
            SewakVaaniController().init(this)
        }
    }.start(wait = true)
}


suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}