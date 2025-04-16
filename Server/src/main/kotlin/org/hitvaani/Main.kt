package org.hitvaani

//import com.devuss.autoproxy.AutoProxyController
//import db.DbHelper
//import db.models.Word
import kidsTeacher.english.EnglishHomeController
//import kidsTeacher.home.KidsLearningController
//import kidsTeacher.math.numbers.MathsController
//import kotlinx.css.CssBuilder
//import org.hitvaani.css.CssController
//import org.hitvaani.home.CrawlerController
//import org.hitvaani.home.HomePageController
//import org.hitvaani.sewakvaani.SewakVaaniController
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlin.time.Duration.Companion.seconds

fun main() {

//    GlobalScope.launch {
//        println("Hello World!")
//        DbHelper.getCollection().apply {
//            insertOne(Word("hello", "नमस्ते", "greeting", listOf("noun", "verb")))
//            find<Word>().collect {
//                println("$it")
//            }
//        }
//    }.start()
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
            staticResources("css", "/css")
//            HomePageController(this)
//            KidsLearningController(this)

//            MathsController(this)
            EnglishHomeController(this)
//
//            CrawlerController(this)
//            AutoProxyController(this)
//            CssController().init(this)
//            SewakVaaniController().init(this)
        }
    }.start(wait = true)

}


//suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
//    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
//}