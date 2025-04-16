//package org.hitvaani.home
//
//import com.bootstrap.components.layout.HorizontalAlignment
//import com.bootstrap.components.layout.Row
//import com.bootstrap.components.layout.VerticalAlignment
//import com.bootstrap.dimens.Height
//import com.bootstrap.dimens.height
//import com.bootstrap.modifier.Modifier
//import com.bootstrap.modifier.classes
//import com.bootstrap.modifier.margin
//import common.NavBar
//import io.ktor.server.html.*
//import io.ktor.server.routing.*
//import kotlinx.html.body
//import kotlinx.html.head
//import kotlinx.html.id
//import kotlinx.html.script
//import common.addBootstrapAndCommon
//
//class HomePageController(routing: Routing) {
//
//    init {
//        addRoutes(routing)
//    }
//
//    private fun addRoutes(routing: Routing) {
//        routing.apply {
//            get("/") {
//                call.respondHtml {
//                    head {
//                        addBootstrapAndCommon {
//                            script(src = "/js/WebVaaniPath.js") {}
//                        }
//                    }
//                    body(Modifier.height(Height.H_AUTO).classes) {
//                        id = "home-page"
////                        Container {
//                        NavBar()
//                        Row(
//                            modifier = Modifier.margin(top = 4).height(Height.H_100),
//                            hAlignment = HorizontalAlignment.CENTER,
//                            vAlignment = VerticalAlignment.CENTER
//                        ) {
//                            id = "home-page-content"
////                            Column(3) {
////                                Card(title = "Tasks") {
////
////                                }
////                            }
////                            Column(3) {
////                                Card(title = "Expenses") {
//////                                    Calendar.getInstance(TimeZone.getDefault()).apply {
//////                                        val date = get(Calendar.DATE)
//////                                        val month = get(Calendar.MONTH) + 1
//////                                        val year = get(Calendar.YEAR)
//////
//////                                        Calendar.FIELD_COUNT
//////
//////                                        +("Date: $date/$month/$year")
//////                                    }
////                                }
////                            }
////                            Column(3) {
////                                Card(title = "Bills") {
////
////                                }
////                            }
////                            Column(3) {
////                                Card(title = "Alerts") {
////
////                                }
////                            }
//                        }
//                    }
//                }
//            }
//        }
////        }
//    }
//}
