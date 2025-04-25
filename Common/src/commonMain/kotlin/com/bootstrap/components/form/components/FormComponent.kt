package com.bootstrap.components.form.components

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.finalize
import kotlinx.html.*

enum class InputSize(val value: String) {
    SMALL("form-control-sm"), LARGE("form-control-lg"),
}

fun Modifier.InputSize(size: InputSize): Modifier {
    return appendClass(size.value)
}

fun Modifier.colorInput(): Modifier {
    return appendClass("form-control-color")
}

inline fun FlowContent.InputField(
    modifier: Modifier = Modifier, type: InputType, crossinline block: INPUT.() -> Unit
) {
    val modifier1 = if (type == InputType.color) {
        modifier.colorInput()
    } else {
        modifier
    }

    val classes = modifier1.finalize("form-control")
    input(classes = classes, type = type) {
        applyAttrFromModifier(modifier1)
        block()
    }
}

inline fun FlowContent.HelperText(
    modifier: Modifier = Modifier, inline: Boolean = false, crossinline block: FlowContent.() -> Unit
) {
    val classes = modifier.finalize("form-text")
    if (inline) {
        span(classes = classes) {
            block()
        }
    } else {
        div(classes = classes) {
            block()
        }
    }
}

inline fun FlowContent.Label(
    modifier: Modifier = Modifier, crossinline block: LABEL.() -> Unit
) {
    val classes = modifier.finalize("form-label")
    label(classes = classes) {
        block()
    }
}

inline fun FlowContent.TextArea(
    modifier: Modifier = Modifier, crossinline block: TEXTAREA.() -> Unit
) {
    val classes = modifier.finalize("form-control")
    textArea(classes = classes) {
        applyAttrFromModifier(modifier)
        block()
    }
}
