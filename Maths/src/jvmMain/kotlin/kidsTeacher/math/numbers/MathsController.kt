package kidsTeacher.math.numbers

import com.bootstrap.colors.Color
import com.bootstrap.colors.backgroundColor
import com.bootstrap.components.cards.Card
import com.bootstrap.components.html.Body
import com.bootstrap.components.layout.HorizontalAlignment
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.VerticalAlignment
import com.bootstrap.dimens.Height
import com.bootstrap.dimens.height
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.classes
import com.bootstrap.modifier.margin
import common.addBootstrapAndCommon
import common.addCommonNavigation
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

class MathsController(route: Route) {

    init {
        addRoutes(route)
    }


    private fun addRoutes(route: Route) {
        route.apply {
            get("/maths/home") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/Maths.js") {}
                        }
                    }
                    Body(Modifier.height(Height.H_AUTO).backgroundColor(Color.GRAY_DARK)) {
                        id = "maths-home-page"
                        addCommonNavigation()
                        Row(
                            modifier = Modifier.margin(top = 4),
                            hAlignment = HorizontalAlignment.CENTER,
                            vAlignment = VerticalAlignment.CENTER
                        ) {
                            id = "maths-home-content"
                            Card {
                                a("/numbers/system", classes = "text-decoration-none") {
                                    id = "number-system-card"
                                    +"Number System"
                                }
                            }
                        }
                    }
                }
            }

            get("/numbers/system") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/Maths.js") {}
                        }
                    }
                    body(Modifier.height(Height.H_AUTO).classes) {
                        id = "number-system-page"
                        addCommonNavigation()
                        Row(
                            modifier = Modifier.margin(top = 4),
                            hAlignment = HorizontalAlignment.CENTER,
                            vAlignment = VerticalAlignment.CENTER
                        ) {
                            id = "number-system-content"

                        }
                    }
                }
            }
        }
    }
}