package common

import kotlinx.html.HEAD
import kotlinx.html.ScriptCrossorigin
import kotlinx.html.link
import kotlinx.html.script

inline fun HEAD.addBootstrapAndCommon(crossinline content: HEAD.() -> Unit) {
    link(rel = "stylesheet", href = "/css/bootstrap.min.css", type = "text/css")
    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    script(src = "/js/bootstrap.bundle.min.js", crossorigin = ScriptCrossorigin.anonymous) {}
    script(src = "/js/Common.js") {}
    link(
        rel = "stylesheet",
        href = "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css",
        type = "text/css"
    )
    content()
}