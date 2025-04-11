package com.bootstrap.colors

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.StyleKey
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.appendStyle


fun Modifier.textColor(value: Color): Modifier {
    return appendClass("text-${value.value}")
}

fun Modifier.backgroundColor(value: ThemedColor): Modifier {
    return appendClass("bg-${value.value}")
}

fun Modifier.backgroundColor(value: Color): Modifier {
    return appendClass(value.value)
}


enum class ThemedColor(val value: String) {
    PRIMARY("primary"), SECONDARY("secondary"), SUCCESS("success"), DANGER("danger"), WARNING("warning"), INFO("info"), LIGHT(
        "light"
    ),
    DARK("dark")
}

enum class Color(val value: String) {
    WHITE("white"), TRANSPARENT("transparent"), BLACK("black"), GRAY("gray"), GRAY_DARK("gray-dark"), BLUE(
        "blue"
    ),
    INDIGO("indigo"), PURPLE("purple"), PINK("pink"), RED("red"), ORANGE("orange"), YELLOW("yellow"), GREEN("green"), TEAL(
        "teal"
    ),
    CYAN("cyan")
}

fun Modifier.hexColor(value: String): Modifier {
    return appendStyle(StyleKey.BACKGROUND_COLOR, value)
}
