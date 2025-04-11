package com.bootstrap.components.button

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.applyModifier
import com.bootstrap.modifier.classes
import kotlinx.html.BUTTON
import kotlinx.html.FlowContent
import kotlinx.html.button


inline fun FlowContent.BUTTON(
    type: ButtonType = ButtonType.BUTTON,
    theme: ButtonTheme = ButtonTheme.PRIMARY,
    size: ButtonSize = ButtonSize.LARGE,
    modifier: Modifier = Modifier,
    crossinline block: BUTTON.() -> Unit
) {
    val finalModifier = modifier.appendClass(type.value).appendClass(theme.value).appendClass(size.value)
    button(classes = "btn ${finalModifier.classes}") {
        applyModifier(finalModifier)
        block()
    }
}

enum class ButtonType(val value: String) {
    BUTTON("button"), RESET("reset"), SUBMIT("submit")
}

enum class ButtonTheme(val value: String) {
    PRIMARY("btn-primary"), SECONDARY("btn-secondary"), SUCCESS("btn-success"), DANGER("btn-danger"), WARNING("btn-warning"), INFO(
        "btn-info"
    ),
    LIGHT("btn-light"), DARK("btn-dark"), LINK("btn-link")
}

enum class ButtonSize(val value: String) {
    LARGE("btn-lg"), SMALL("btn-sm"), BLOCK("btn-block")
}