package com.bootstrap.colors

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass


fun Modifier.textColor(value: Color): Modifier {
    return appendClass("text-${value.value}")
}

fun Modifier.textColor(value: ThemedColor): Modifier {
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
    CYAN("cyan"), WHITE_50("white-50"), BLACK_50("black-50"), GRAY_100("gray-100"), GRAY_200("gray-200"), GRAY_300("gray-300"), GRAY_400(
        "gray-400"
    ),
    GRAY_500("gray-500"), GRAY_600("gray-600"), GRAY_700("gray-700"), GRAY_800("gray-800"), GRAY_900("gray-900"), BLUE_100(
        "blue-100"
    ),
    BLUE_200("blue-200"), BLUE_300("blue-300"), BLUE_400("blue-400"), BLUE_500("blue-500"), BLUE_600("blue-600"), BLUE_700(
        "blue-700"
    ),
    BLUE_800("blue-800"), BLUE_900("blue-900"), INDIGO_100("indigo-100"), INDIGO_200("indigo-200"), INDIGO_300("indigo-300"), INDIGO_400(
        "indigo-400"
    ),
    INDIGO_500("indigo-500"), INDIGO_600("indigo-600"), INDIGO_700("indigo-700"), INDIGO_800("indigo-800"), INDIGO_900("indigo-900"), PURPLE_100(
        "purple-100"
    ),
    PURPLE_200("purple-200"), PURPLE_300("purple-300"), PURPLE_400("purple-400"), PURPLE_500("purple-500"), PURPLE_600("purple-600"), PURPLE_700(
        "purple-700"
    ),
    PURPLE_800("purple-800"), PURPLE_900("purple-900"), PINK_100("pink-100"), PINK_200("pink-200"), PINK_300("pink-300"), PINK_400(
        "pink-400"
    ),
    PINK_500("pink-500"), PINK_600("pink-600"), PINK_700("pink-700"), PINK_800("pink-800"), PINK_900("pink-900"), RED_100(
        "red-100"
    ),
    RED_200("red-200"), RED_300("red-300"), RED_400("red-400"), RED_500("red-500"), RED_600("red-600"), RED_700("red-700"), RED_800(
        "red-800"
    ),
    RED_900("red-900"), ORANGE_100("orange-100"), ORANGE_200("orange-200"), ORANGE_300("orange-300"), ORANGE_400("orange-400"), ORANGE_500(
        "orange-500"
    ),
    ORANGE_600("orange-600"), ORANGE_700("orange-700"), ORANGE_800("orange-800"), ORANGE_900("orange-900"), YELLOW_100("yellow-100"), YELLOW_200(
        "yellow-200"
    ),
    YELLOW_300("yellow-300"), YELLOW_400("yellow-400"), YELLOW_500("yellow-500"), YELLOW_600("yellow-600"), YELLOW_700("yellow-700"), YELLOW_800(
        "yellow-800"
    ),
    YELLOW_900("yellow-900"), GREEN_100("green-100"), GREEN_200("green-200"), GREEN_300("green-300"), GREEN_400("green-400"), GREEN_500(
        "green-500"
    ),
    GREEN_600("green-600"), GREEN_700("green-700"), GREEN_800("green-800"), GREEN_900("green-900"), TEAL_100("teal-100"), TEAL_200(
        "teal-200"
    ),
    TEAL_300("teal-300"), TEAL_400("teal-400"), TEAL_500("teal-500"), TEAL_600("teal-600"), TEAL_700("teal-700"), TEAL_800(
        "teal-800"
    ),
    TEAL_900("teal-900"), CYAN_100("cyan-100"), CYAN_200("cyan-200"), CYAN_300("cyan-300"), CYAN_400("cyan-400"), CYAN_500(
        "cyan-500"
    ),
    CYAN_600("cyan-600"), CYAN_700("cyan-700"), CYAN_800("cyan-800"), CYAN_900("cyan-900"),
}
