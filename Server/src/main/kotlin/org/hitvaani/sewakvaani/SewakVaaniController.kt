//package org.hitvaani.sewakvaani
//
//import io.ktor.server.application.*
//import io.ktor.server.html.*
//import io.ktor.server.response.*
//import io.ktor.server.routing.*
//import kotlinx.html.*
//import kotlinx.serialization.json.Json
//import org.hitvaani.VersesInfo
//
//class SewakVaaniController {
//
//    val json = this::class.java.getResource("/json/verses_sevakvani.json").readText()
//    val sewakWani = Json.decodeFromString<List<VersesInfo>>(json)
//
//
//    suspend fun index(call: ApplicationCall) {
//        call.respondHtml {
//            head {
//                link(rel = "stylesheet", href = "/styles.css", type = "text/css")
//            }
//            body {
//                sewakWani.forEach {
//                    div {
//                        div {
//                            ul {
//                                li {
//                                    h6("verse-title") {
//                                        +it.verseNumber
//                                    }
//                                    p("verse-text") {
//                                        unsafe {
//                                            raw(it.verseText.replace("\n", "<br/>", true))
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        div {
//
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    fun init(routing: Routing) {
//        routing.apply {
//            get("/sewakvaani") {
//                SewakVaaniController().index(call)
//            }
//            get("/api/sewakvaani") {
//                call.respond(sewakWani)
//            }
//        }
//    }
//}