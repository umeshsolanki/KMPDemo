package kidsTeacher.hindi

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
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script

class HindiHomeController(route: Route) {

    init {
        addRoutes(route)
    }


    private fun addRoutes(route: Route) {
        route.apply {
            get("/hi/home") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/KidsTeacher.js") {}
                        }
                    }
                    body(Modifier.height(Height.H_AUTO).classes) {
                        id = "hi-page"
                        NavBar()
                        Row(
                            modifier = Modifier.margin(top = 4),
                            hAlignment = HorizontalAlignment.CENTER,
                            vAlignment = VerticalAlignment.CENTER
                        ) {
                            id = "hi-content"

                        }
                    }
                }
            }
        }
    }
}