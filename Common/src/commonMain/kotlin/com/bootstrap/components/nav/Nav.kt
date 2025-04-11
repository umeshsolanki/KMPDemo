package com.bootstrap.components.nav

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.applyModifier
import com.bootstrap.modifier.finalize
import kotlinx.html.*

inline fun FlowContent.NavBar(modifier: Modifier = Modifier, crossinline block: NAV.() -> Unit) {
    nav(classes = modifier.finalize("navbar navbar-expand-lg navbar-light bg-light")) {
        applyModifier(modifier)
        block()
    }
}

inline fun UL.NAVLI(modifier: Modifier = Modifier, crossinline block: FlowContent.() -> Unit) {
    val classes = modifier.finalize("nav-item")
    li(classes = classes) {
        applyModifier(modifier)
        block()
    }
}

inline fun FlowContent.NavUL(modifier: Modifier = Modifier, crossinline block: FlowContent.() -> Unit) {
    val classes = modifier.finalize("navbar-nav")
    ul(classes = classes) {
        applyModifier(modifier)
        block()
    }
}

inline fun FlowContent.NavLink(modifier: Modifier = Modifier, crossinline block: A.() -> Unit) {
    val classes = modifier.finalize("nav-link")
    a(classes = classes) {
        applyModifier(modifier)
        block()
    }
}

inline fun FlowContent.NavBarBrand(modifier: Modifier = Modifier, crossinline block: A.() -> Unit) {
    val classes = modifier.finalize("navbar-brand")
    a(classes = classes) {
        applyModifier(modifier)
        block()
    }
}

inline fun FlowContent.NavBarToggle(modifier: Modifier = Modifier, crossinline block: BUTTON.() -> Unit) {
    val classes = modifier.finalize("navbar-toggler")
    button(classes = classes) {
        applyModifier(modifier)
        attributes["type"] = "button"
        attributes["data-bs-toggle"] = "collapse"
        attributes["data-bs-target"] = "#navbarNav"
        attributes["aria-controls"] = "navbarNav"
        attributes["aria-expanded"] = "false"
        attributes["aria-label"] = "Toggle navigation"
        block()
    }
}

inline fun FlowContent.NavBarCollapse(modifier: Modifier = Modifier, crossinline block: DIV.() -> Unit) {
    val classes = modifier.finalize("collapse navbar-collapse")
    div(classes = classes) {
        attributes["id"] = "navbarNav"
        block()
    }
}

inline fun FlowContent.NavBarToggleIcon(modifier: Modifier = Modifier, crossinline block: SPAN.() -> Unit) {
    val classes = modifier.finalize("navbar-toggler-icon")
    span(classes = classes) {
        block()
    }
}
