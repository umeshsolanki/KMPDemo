//package org.hitvaani.css
//
//import io.ktor.server.routing.*
//import kotlinx.css.*
//import org.hitvaani.respondCss
//
//class CssController {
//    fun init(routing: Routing) {
//        routing.apply {
//            get("/styles.css") {
//                call.respondCss {
//                    body {
//                        backgroundColor = Color.black
//                        color = Color.white
//                        margin = Margin(0.px)
//                    }
//                    rule("verse-title") {
//                        color = Color.white
//                    }
//                    rule("verse-text") {
//                        color = Color.white
//                    }
//                }
//            }
//        }
//    }
//
//}
