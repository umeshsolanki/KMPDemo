package com.bootstrap.components.loader

import com.bootstrap.colors.ThemedColor
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.classes
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.div

enum class LoaderSize(val className: String) {
    SMALL("spinner-border-sm"), LARGE("spinner-border-lg"),
}

inline fun FlowContent.Loader(
    modifier: Modifier = Modifier,
    size: LoaderSize? = null,
    color: ThemedColor = ThemedColor.PRIMARY,
    asBorder: Boolean = true,
    asGrow: Boolean = false,
    crossinline items: DIV.() -> Unit = {}
) {
    val modifier1 = modifier.appendClass("text-${color.value}").apply {
        if (asBorder) {
            appendClass("spinner-border")
        }
        if (asGrow) {
            appendClass("spinner-grow")
        }
        size?.let { appendClass(it.className) }
    }
    div(modifier1.classes) {
        applyAttrFromModifier(modifier1)
        items()
    }
}