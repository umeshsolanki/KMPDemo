package com.bootstrap.components.html

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.classes
import kotlinx.html.FlowContent
import kotlinx.html.P
import kotlinx.html.p

inline fun FlowContent.P(
    modifier: Modifier = Modifier, crossinline items: P.() -> Unit = {}
) {
    p(classes = modifier.classes) {
        applyAttrFromModifier(modifier)
        items()
    }
}