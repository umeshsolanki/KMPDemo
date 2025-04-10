package com.bootstrap.border

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass

enum class BorderWidth(val value: String) {
    BORDER_0("0"), BORDER_1("1"), BORDER_2("2"), BORDER_3("3"), BORDER_4("4"), BORDER_5("5")
}

enum class BorderTheme(val value: String) {
    PRIMARY("border-primary"), SECONDARY("border-secondary"), SUCCESS("border-success"), DANGER("border-danger"), WARNING(
        "border-warning"
    ),
    INFO(
        "border-info"
    ),
    LIGHT("border-light"), DARK("border-dark"), WHITE("border-white")
}

enum class BorderRadius(val value: String) {
    NONE(""), ROUNDED("rounded"), ROUNDED_TOP("rounded-top"), ROUNDED_END("rounded-end"), ROUNDED_BOTTOM("rounded-bottom"), ROUNDED_START(
        "rounded-start"
    ),
    ROUNDED_CIRCLE("rounded-circle"), ROUNDED_PILL("rounded-pill"), ROUNDED_0("rounded-0"), ROUNDED_1("rounded-1"), ROUNDED_2(
        "rounded-2"
    ),
    ROUNDED_3("rounded-3")
}

enum class BorderStyle(val value: String) {
    SOLID("border-solid"), DOTTED("border-dotted"), DASHED("border-dashed"), DOUBLE("border-double"), NONE("border-none")
}

enum class BorderType(val value: String) {
    ALL("border"), TOP("border-top"), END("border-end"), BOTTOM("border-bottom"), START("border-start"),
}

fun Modifier.border(
    type: BorderType = BorderType.ALL,
    width: BorderWidth = BorderWidth.BORDER_1,
    theme: BorderTheme = BorderTheme.PRIMARY,
    style: BorderStyle = BorderStyle.SOLID,
    radius: BorderRadius = BorderRadius.NONE
): Modifier {
    return appendClass(type.value).appendClass(width.value).appendClass(theme.value).appendClass(style.value)
        .appendClass(radius.value)
}
