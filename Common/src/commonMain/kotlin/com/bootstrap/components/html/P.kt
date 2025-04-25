package com.bootstrap.components.html

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.classes
import kotlinx.html.*


inline fun HTML.Body(
    modifier: Modifier = Modifier, crossinline items: BODY.() -> Unit
) {
    body(classes = modifier.classes) {
        applyAttrFromModifier(modifier)
        items()
    }
}

inline fun FlowContent.P(
    modifier: Modifier = Modifier, crossinline items: P.() -> Unit = {}
) {
    p(classes = modifier.classes) {
        applyAttrFromModifier(modifier)
        items()
    }
}