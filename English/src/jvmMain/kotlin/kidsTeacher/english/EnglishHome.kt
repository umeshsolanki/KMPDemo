package kidsTeacher.english

import com.bootstrap.colors.Color
import com.bootstrap.colors.backgroundColor
import com.bootstrap.components.cards.Card
import com.bootstrap.components.html.Body
import com.bootstrap.components.layout.Column
import com.bootstrap.components.layout.HorizontalAlignment
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.VerticalAlignment
import com.bootstrap.dimens.Height
import com.bootstrap.dimens.height
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.margin
import common.addBootstrapAndCommon
import common.addCommonNavigation
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kidsTeacher.english.words.WordsController
import kotlinx.html.a
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script

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
                            script(src = "/js/KidsTeacher.js") {

                            }
                        }
                    }
                    Body(Modifier.height(Height.H_AUTO).backgroundColor(Color.GRAY_DARK)) {
                        id = "en-page"
                        addCommonNavigation()
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