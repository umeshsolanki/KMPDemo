package com.bootstrap.colors

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.StyleKey
import com.bootstrap.modifier.appendClass
import com.bootstrap.modifier.appendStyle


fun Modifier.textColor(value: ThemedColor): Modifier {
    return appendClass("text-${value.value}")
}

fun Modifier.backgroundColor(value: ThemedColor): Modifier {
    return appendClass("bg-${value.value}")
}

enum class ThemedColor(val value: String) {
    PRIMARY("primary"), SECONDARY("secondary"), SUCCESS("success"), DANGER("danger"), WARNING("warning"), INFO("info"), LIGHT(
        "light"
    ),
    DARK("dark")
}


enum class Color(val code: String) {
    WHITE("#FFFFFF"), TRANSPARENT("#00000000"), BLACK("#000000"), GRAY("#808080"), GRAY_DARK("#343a40"), BLUE(
        "#007bff"
    ),
    INDIGO("#6610f2"), PURPLE("#6f42c1"), PINK("#e83e8c"), RED("#dc3545"), ORANGE("#fd7e14"), YELLOW("#ffc107"), GREEN(
        "#28a745"
    ),
    TEAL("#20c997"), CYAN("#17a2b8"), GRAY_100("#f8f9fa"), GRAY_200("#e9ecef"), GRAY_300("#dee2e6"), GRAY_400("#ced4da"), GRAY_500(
        "#adb5bd"
    ),
    GRAY_600(
        "#6c757d"
    ),
    GRAY_700("#495057"), GRAY_800("#343a40"), GRAY_900("#212529"), BLUE_100("#cce5ff"), BLUE_200("#b8daff"), BLUE_300("#80bdff"), BLUE_400(
        "#339af0"
    ),
    BLUE_500("#007bff"), BLUE_600(
        "#0062cc"
    ),
    BLUE_700("#005cbf"), BLUE_800("#004085"), BLUE_900("#002752"), INDIGO_100("#e9ecef"), INDIGO_200("#cfe2ff"), INDIGO_300(
        "#a6c8ff"
    ),
    INDIGO_400("#85b7ff"), INDIGO_500(
        "#6610f2"
    ),
    INDIGO_600("#520dc2"), INDIGO_700("#4e0ec2"), INDIGO_800("#4b0ec2"), INDIGO_900("#4a0ec2"), PURPLE_100("#f3e8ff"), PURPLE_200(
        "#e2c2ff"
    ),
    PURPLE_300("#d1a6ff"), PURPLE_400("#c18eff"), PURPLE_500(
        "#6f42c1"
    ),
    PURPLE_600("#5f3dc4"), PURPLE_700("#4f2ec7"), PURPLE_800("#3f1ec9"), PURPLE_900("#2f0ecb"), PINK_100("#f8d7da"), PINK_200(
        "#f5c6cb"
    ),
    PINK_300("#f1b0b7"), PINK_400("#ec9a9e"), PINK_500(
        "#e83e8c"
    ),
    PINK_600("#d63384"), PINK_700("#c72b7a"), PINK_800("#b71f70"), PINK_900("#a71266"), RED_100("#f8d7da"), RED_200("#f5c6cb"), RED_300(
        "#f1b0b7"
    ),
    RED_400("#ec9a9e"), RED_500(
        "#dc3545"
    ),
    RED_600("#d02a3a"), RED_700("#c0202f"), RED_800("#b01624"), RED_900("#a00c19"), ORANGE_100("#fff3cd"), ORANGE_200("#ffeeba"), ORANGE_300(
        "#ffdd57"
    ),
    ORANGE_400("#ffc107"), ORANGE_500(
        "#fd7e14"
    ),
    ORANGE_600("#f76707"), ORANGE_700("#e8590c"), ORANGE_800("#dc6a0d"), ORANGE_900("#d77b0e"), YELLOW_100("#fff3cd"), YELLOW_200(
        "#ffeeba"
    ),
    YELLOW_300("#ffdd57"), YELLOW_400("#ffc107"), YELLOW_500(
        "#ffc107"
    ),
    YELLOW_600("#e0a800"), YELLOW_700("#d39e00"), YELLOW_800("#c69500"), YELLOW_900("#b68d00"), GREEN_100("#d4edda"), GREEN_200(
        "#c3e6cb"
    ),
    GREEN_300("#b1dfbb"), GREEN_400("#8fd19e"), GREEN_500(
        "#28a745"
    ),
    GREEN_600("#218838"), GREEN_700("#1e7e34"), GREEN_800("#1c7430"), GREEN_900("#1a6b2c"), TEAL_100("#d1ecf1"), TEAL_200(
        "#bee5eb"
    ),
    TEAL_300("#abdde5"), TEAL_400("#8bd3e6"), TEAL_500(
        "#20c997"
    ),
    TEAL_600("#1c7a8c"), TEAL_700("#177f8d"), TEAL_800("#11858b"), TEAL_900("#0b8b8a"), CYAN_100("#cce5ff"), CYAN_200("#b8daff"), CYAN_300(
        "#80bdff"
    ),
    CYAN_400("#339af0"), CYAN_500(
        "#17a2b8"
    ),
    CYAN_600("#138496"), CYAN_700("#117a8b"), CYAN_800("#0f6f7d"), CYAN_900("#0c646f"),
}

fun Modifier.hexBgColor(value: String): Modifier {
    return appendStyle(StyleKey.BACKGROUND_COLOR, value)
}

fun Modifier.hexBgColor(color: Color): Modifier {
    return appendStyle(StyleKey.BACKGROUND_COLOR, color.code)
}

fun Modifier.hexTextColor(value: String): Modifier {
    return appendStyle(StyleKey.COLOR, value)
}

fun Modifier.hexTextColor(color: Color): Modifier {
    return appendStyle(StyleKey.COLOR, color.code)
}
