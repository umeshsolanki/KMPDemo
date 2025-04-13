package kidsTeacher.home

import com.bootstrap.components.cards.Card
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.HorizontalAlignment
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.VerticalAlignment
import com.bootstrap.dimens.Height
import com.bootstrap.dimens.height
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.classes
import com.bootstrap.modifier.margin
import common.NavBar
import common.addBootstrapAndCommon
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

class KidsLearningController(route: Route) {

    init {
        addKidsHomeRoutes(route)
    }

    private fun addKidsHomeRoutes(route: Route) {
        route.apply {
            get("/kids") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/KidsTeacher.js") {}
                        }
                    }
                    body(Modifier.height(Height.H_AUTO).classes) {
                        id = "kids-home-page"
                        NavBar()
                        Row(
                            modifier = Modifier.margin(top = 4),
                            hAlignment = HorizontalAlignment.CENTER,
                            vAlignment = VerticalAlignment.CENTER
                        ) {
                            Column(alignment = VerticalAlignment.CENTER) {
                                a("/maths/home", classes = "text-decoration-none") {
                                    Card(title = "Maths") {
                                        +"Maths"
                                    }
                                }
                            }
                            Column {
                                a("/en/home", classes = "text-decoration-none") {
                                    Card(title = "English") {
                                        +"English"
                                    }
                                }
                            }
                            Column {
                                a("/hi/home", classes = "text-decoration-none") {
                                    Card(title = "Hindi") {
                                        +"Hindi"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}