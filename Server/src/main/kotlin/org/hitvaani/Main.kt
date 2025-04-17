package org.hitvaani

//import com.devuss.autoproxy.AutoProxyController
//import org.hitvaani.home.CrawlerController
//import org.hitvaani.sewakvaani.SewakVaaniController
import db.rdms.RdbHelper
import db.rdms.tables.TableProvider
import db.rdms.tables.TableRegistry
import db.rdms.tables.TestEntity
import db.rdms.tables.TestTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kidsTeacher.english.EnglishHomeController
import kidsTeacher.home.KidsLearningController
import kidsTeacher.math.numbers.MathsController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.css.CssBuilder
import org.hitvaani.home.HomePageController
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.time.Duration.Companion.seconds

@OptIn(DelicateCoroutinesApi::class)
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
            staticResources("css", "/css")
            HomePageController(this)
            KidsLearningController(this)
            MathsController(this)
            EnglishHomeController(this)
            TableRegistry.register(object : TableProvider {
                override fun listTables(): List<Table> {
                    return listOf(TestTable)
                }
            })
            RdbHelper.createDbAndTables()
            transaction(RdbHelper.getDatabase()) {
//                TestTable.selectAll()
//                TestEntity.findById(1)?.let {
//                    it.name
//                    println(it.name)
//                }
            }
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