package org.hitvaani

import db.rdms.RdbHelper
import db.rdms.tables.TableProvider
import db.rdms.tables.TableRegistry
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kidsTeacher.english.EnglishHomeController
import kidsTeacher.english.db.WordsTable
import kidsTeacher.home.KidsLearningController
import kidsTeacher.math.numbers.MathsController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.css.CssBuilder
import kotlinx.serialization.json.Json
import org.hitvaani.home.HomePageController
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.time.Duration.Companion.seconds

@OptIn(DelicateCoroutinesApi::class)
fun main() {

    embeddedServer(Netty, port = 8080) {
        install(CORS) {
            anyHost()
        }
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        install(WebSockets) {
            pingPeriod = 15.seconds
            timeout = 15.seconds
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }
        routing {
            staticResources("js", "/js")
            staticResources("css", "/css")
            staticResources("audio", "/audio")
            HomePageController(this)
            KidsLearningController(this)
            MathsController(this)
            EnglishHomeController(this)
            TableRegistry.register(object : TableProvider {
                override fun listTables(): List<Table> {
                    return listOf(WordsTable)
                }
            })
            RdbHelper.createDbAndTables()
//            CrawlerController(this)
//            AutoProxyController(this)
//            CssController().init(this)
//            SewakVaaniController().init(this)
        }
    }.start(wait = true)

}


suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}