package com.bootstrap.components.layout

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.classes
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.div

inline fun FlowContent.Row(
    modifier: Modifier = Modifier,
    hAlignment: HorizontalAlignment? = null,
    vAlignment: VerticalAlignment? = null,
    crossinline block: DIV.() -> Unit
) {
    var modifier1 = hAlignment?.let {
        modifier.alignHorizontally(it)
    } ?: modifier
    if (vAlignment != null) {
        modifier1 = modifier1.alignVertically(vAlignment)
    }
    div(modifier1.appendClass("row").classes) {
        applyAttrFromModifier(modifier1)
        block()
    }
}

inline fun FlowContent.Container(
    modifier: Modifier = Modifier, fluid: Boolean = false, crossinline block: DIV.() -> Unit
) {
    val classes = if (fluid) {
        "container-fluid"
    } else {
        "container"
    }
    div(modifier.appendClass(classes).classes) {
        applyAttrFromModifier(modifier)
        block()
    }
}

inline fun FlowContent.Column(
    size: Int = -1,
    modifier: Modifier = Modifier,
    alignment: VerticalAlignment? = null,
    crossinline block: DIV.() -> Unit
) {
    val sizeClass = if (size > 0) "-$size" else ""

    val modifier1 = if (alignment != null) {
        modifier.alignSelfVertically(alignment)
    } else {
        modifier
    }
    div(modifier1.appendClass("col$sizeClass").classes) {
        applyAttrFromModifier(modifier1)
        block()
    }
}

enum class HorizontalAlignment(val value: String) {
    START("start"), CENTER("center"), END("end"), AROUND("around"), BETWEEN("between"), EVENLY("evenly")
}

enum class VerticalAlignment(val value: String) {
    TOP("top"), CENTER("center"), BOTTOM("end")
}

enum class TextAlignment {
    START, CENTER, END
}

fun Modifier.alignText(
    alignment: TextAlignment = TextAlignment.START,
): Modifier {
    return appendClass("text-${alignment.name.lowercase()}")
}

fun Modifier.alignVertically(
    alignment: VerticalAlignment = VerticalAlignment.TOP,
): Modifier {
    return appendClass("align-items-${alignment.value}")
}

fun Modifier.alignSelfVertically(
    alignment: VerticalAlignment = VerticalAlignment.TOP,
): Modifier {
    return appendClass("align-self-${alignment.value}")
}

fun Modifier.alignHorizontally(
    alignment: HorizontalAlignment = HorizontalAlignment.START,
): Modifier {
    return appendClass("justify-content-${alignment.value}")
}

fun Modifier.spaced(
    all: Int = 0,
): Modifier {
    return appendClass("g-$all")
}

fun Modifier.spaced(
    horizontal: Int,
    vertical: Int,
): Modifier {
    if (horizontal == vertical) {
        return appendClass("g-$horizontal")
    }
    if (horizontal > 0 && vertical > 0) {
        return appendClass("gx-$horizontal gy-$vertical")
    } else if (horizontal > 0) {
        return appendClass("gx-$horizontal")
    } else if (vertical > 0) {
        return appendClass("gy-$vertical")
    } else if (horizontal == 0) {
        return appendClass("gx-0")
    }
    return appendClass("gy-0")
}
