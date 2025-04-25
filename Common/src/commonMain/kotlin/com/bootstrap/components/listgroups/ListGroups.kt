package com.bootstrap.components.listgroups

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.classes
import kotlinx.html.*

inline fun FlowContent.ListGroup(
    modifier: Modifier = Modifier,
    asFlush: Boolean = false,
    horizontal: Boolean = false,
    crossinline items: UL.() -> Unit
) {
    val modifier1 = modifier.appendClass("list-group").apply {
        if (horizontal) {
            appendClass("list-group-horizontal")
        }
        if (asFlush) {
            appendClass("list-group-flush")
        }
    }
    ul(modifier1.classes) {
        applyAttrFromModifier(modifier1)
        items()
    }
}


inline fun FlowContent.DivListGroup(
    modifier: Modifier = Modifier,
    asFlush: Boolean = false,
    horizontal: Boolean = false,
    crossinline items: DIV.() -> Unit
) {
    val modifier1 = modifier.appendClass("list-group").apply {
        if (horizontal) {
            appendClass("list-group-horizontal")
        }
        if (asFlush) {
            appendClass("list-group-flush")
        }
    }
    div(modifier1.classes) {
        applyAttrFromModifier(modifier1)
        items()
    }
}

inline fun DIV.AListGroupItemAction(
    modifier: Modifier = Modifier, crossinline items: A.() -> Unit
) {
    val modifier1 = modifier.appendClass("list-group-item").appendClass("list-group-item-action")
    a(modifier1.classes) {
        applyAttrFromModifier(modifier1)
        items()
    }
}

inline fun DIV.ButtonListGroupItemAction(
    modifier: Modifier = Modifier, crossinline item: BUTTON.() -> Unit
) {
    val modifier1 = modifier.appendClass("list-group-item").appendClass("list-group-item-action")
    button(classes = modifier1.classes, type = ButtonType.button) {
        applyAttrFromModifier(modifier1)
        item()
    }
}

inline fun UL.ListGroupItem(
    modifier: Modifier = Modifier, crossinline items: LI.() -> Unit
) {
    val modifier1 = modifier.appendClass("list-group-item")
    li(modifier1.classes) {
        applyAttrFromModifier(modifier1)
        items()
    }
}

