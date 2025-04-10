package util

import kotlinx.browser.document
import kotlinx.dom.clear
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.dom.append

fun <T> getElementById(id: String): T? {
    return document.getElementById(id) as? T
}

fun setContent(id: String, content: FlowContent.() -> Unit) {
    document.getElementById(id)?.apply {
        clear()
        append {
            div {
                content()
            }
        }
    }
}

fun append(id: String, content: FlowContent.() -> Unit) {
    document.getElementById(id)?.innerHTML = ""

}

fun prepend(id: String, content: FlowContent.() -> Unit) {
    document.getElementById(id)?.innerHTML = ""

}