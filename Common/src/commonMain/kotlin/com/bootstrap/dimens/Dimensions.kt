package com.bootstrap.dimens

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.appendClass

fun Modifier.width(width: Width): Modifier {
    return appendClass(width.value)
}

fun Modifier.height(height: Height): Modifier {
    return appendClass(height.value)
}

fun Modifier.width(width: Int): Modifier {
    return appendClass("w-${width}%")
}

enum class Width(val value: String) {
    W_25("w-25"), W_50("w-50"), W_75("w-75"), W_100("w-100"), W_AUTO("w-auto"), W_0("w-0"), VW_100("vw-100")
}

enum class Height(val value: String) {
    H_25("h-25"), H_50("h-50"), H_75("h-75"), H_100("h-100"), H_AUTO("h-auto"), H_0("h-0"), VH_100("vh-100")
}