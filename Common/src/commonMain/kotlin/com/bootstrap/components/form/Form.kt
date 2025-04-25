package com.bootstrap.components.form

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.finalize
import kotlinx.html.FORM
import kotlinx.html.FlowContent
import kotlinx.html.form

inline fun FlowContent.FORM(modifier: Modifier = Modifier, crossinline block: FORM.() -> Unit) {
    form(classes = modifier.finalize("form")) {
        applyAttrFromModifier(modifier)
        block()
    }
}