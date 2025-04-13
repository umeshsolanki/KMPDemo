package common

import com.bootstrap.components.nav.*
import kotlinx.html.BODY
import kotlinx.html.id

fun BODY.NavBar() {
    NavBar {
        NavBarBrand {
            href = "/"
            +"Hitvaani"
        }
        NavBarToggle {
            NavBarToggleIcon {}
        }
        NavBarCollapse {
            NavUL {
                NavLink {
                    href = "/"
                    +"Home"
                }
//                NavLink {
//                    href = "/crawler"
//                    +"Crawler"
//                }
                NavLink {
                    href = "/kids"
                    +"Kids Tutorial"
                }
                NavLink {
                    id = "tts-toggle"
                }
            }
        }
    }
}