package kidsTeacher.english

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
import kidsTeacher.english.words.WordsController
import kotlinx.html.*

class EnglishHomeController(route: Route) {

    init {
        addRoutes(route)
        WordsController(route)
    }


    private fun addRoutes(route: Route) {
        route.apply {
            get("/en/home") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/KidsTeacher.js") {}
                        }
                    }
                    body(Modifier.height(Height.H_AUTO).classes) {
                        id = "en-page"
                        NavBar()
                        Row(
                            modifier = Modifier.margin(top = 4),
                            hAlignment = HorizontalAlignment.CENTER,
                            vAlignment = VerticalAlignment.CENTER
                        ) {
                            id = "en-content"
                            Column(3) {
                                Card {
                                    a("/en/words", classes = "text-decoration-none") {
                                        +"Words"
                                    }
                                }
                            }
                            Column(3) {
                                Card {
                                    a("/en/words/test", classes = "text-decoration-none") {
                                        +"Words Test"
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