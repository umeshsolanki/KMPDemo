package com.bootstrap.dimens

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass

fun Modifier.width(width: Width): Modifier {
    return appendClass("w-${width.value}")
}

fun Modifier.height(height: Height): Modifier {
    return appendClass("h-${height.value}")
}

enum class Width(val value: String) {
    W_25("25"), W_50("50"), W_75("75"), W_100("100"), W_AUTO("auto"), W_0("0")
}

enum class Height(val value: String) {
    H_25("25"), H_50("50"), H_75("75"), H_100("100"), H_AUTO("auto"), H_0("0")
}