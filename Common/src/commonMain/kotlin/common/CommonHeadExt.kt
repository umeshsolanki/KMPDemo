package common

import kotlinx.html.HEAD
import kotlinx.html.ScriptCrossorigin
import kotlinx.html.link
import kotlinx.html.script

inline fun HEAD.addBootstrapAndCommon(crossinline content: HEAD.() -> Unit) {
    link(rel = "stylesheet", href = "/css/bootstrap.min.css", type = "text/css")
//    .apply {
//        attributes["integrity"] = "sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
//        attributes["crossorigin"] = "anonymous"
//    }
    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    script(
        src = "/js/bootstrap.bundle.min.js", crossorigin = ScriptCrossorigin.anonymous
    ) {
//        attributes["integrity"] = "sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    }
    script("/js/Common.js") {}
    content()
}