package com.bootstrap.modifier

interface Modifier {
    companion object : Modifier
}

fun Modifier.appendClass(clazz: String): Modifier {
    if (clazz.isBlank()) {
        return this
    }
    return (this as? CssModifier)?.addClass(clazz) ?: CssModifier(clazz)
}

fun Modifier.finalize(clazz: String): String {
    if (clazz.isBlank()) {
        return classes
    }
    return (this as? CssModifier)?.addClass(clazz)?.classes ?: CssModifier(clazz).classes
}

fun Modifier.padding(all: Int): Modifier {
    return appendClass("p-$all")
}

fun Modifier.padding(horizontal: Int = 0, vertical: Int = 0): Modifier {
    return appendClass("px-$horizontal py-$vertical")
}

fun Modifier.padding(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0): Modifier {
    return appendClass("pt-$top ps-$start pe-$end pb-$bottom")
}


fun Modifier.margin(all: Int): Modifier {
    return appendClass("m-$all")
}

fun Modifier.margin(horizontal: Int = 0, vertical: Int = 0): Modifier {
    return appendClass("mx-$horizontal my-$vertical")
}

fun Modifier.margin(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0): Modifier {
    return appendClass("mt-$top ms-$start me-$end mb-$bottom")
}


fun Modifier.displayInline(): Modifier {
    return appendClass("d-inline")
}

fun Modifier.displayInBlock(): Modifier {
    return appendClass("d-block")
}

enum class Opacity(val value: String) {
    O_0("0"), O_25("25"), O_50("50"), O_75("75"), O_100("100")
}

fun Modifier.opacity(value: Opacity): Modifier {
    return appendClass("opacity-${value.value}")
}

fun Modifier.displayFlex(): Modifier {
    return appendClass("d-flex")
}

fun Modifier.visibility(visible: Boolean): Modifier {
    return appendClass(if (visible) "visible" else "invisible")
}

val Modifier.classes: String
    get() {
        val classes = (this as? CssModifier)?.classes
        return if (classes.isNullOrBlank()) {
            ""
        } else {
            classes
        }
    }

class CssModifier(classes: String) : Modifier {
    private var classList: MutableList<String?> = mutableListOf()

    init {
//        println("init")
        addClass(classes)
    }

    fun addClass(value: String): CssModifier {
//        println("Adding class $value")
        classList.add(value)
        return this
    }

    val classes: String
        get() {
//            println("Getting classes: {${classList.size}}")
            return classList.joinToString(" ")
        }
}


