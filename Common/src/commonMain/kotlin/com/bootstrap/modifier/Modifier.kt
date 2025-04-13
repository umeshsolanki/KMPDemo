package com.bootstrap.modifier

import kotlinx.html.CoreAttributeGroupFacade
import kotlinx.html.style

interface Modifier {
    companion object : Modifier
}

fun Modifier.appendClass(clazz: String): Modifier {
    if (clazz.isBlank()) {
        return this
    }
    return (this as? ModifierImpl)?.addClass(clazz) ?: ModifierImpl(clazz)
}

fun Modifier.appendStyle(key: StyleKey, value: Any): Modifier {
    return (this as? ModifierImpl)?.addStyle(key, value) ?: ModifierImpl("").addStyle(key, value)
}

fun Modifier.finalize(clazz: String): String {
    if (clazz.isBlank()) {
        return classes
    }
    return (this as? ModifierImpl)?.addClass(clazz)?.classes ?: ModifierImpl(clazz).classes
}

fun CoreAttributeGroupFacade.applyModifier(modifier: Modifier) {
    modifier.style?.let {
        if (it.isNotBlank()) {
//            println("Setting style= $it")
            style = it
        }
    }
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
        val classes = (this as? ModifierImpl)?.clazzes
        return if (classes.isNullOrBlank()) {
            ""
        } else {
            classes
        }
    }

val Modifier.style: String?
    get() {
        val styles = (this as? ModifierImpl)?._styles ?: return null
        if (styles.isEmpty()) {
            return null
        }
        val finalStyle = buildString {
            styles.forEach {
                append(it.first.value)
                append(":")
//                if (it.second.instanceOf(String::class)) {
//                    append(it.second)
//                } else {
                append(it.second)
//                }
                append(";")
            }
        }
        println("Final Style: $finalStyle")
        return finalStyle
    }

//fun Modifier.styled(builder: CssBuilder.() -> Unit): Modifier {
//    return StyleModifier(value)
//}
//
//class StyleModifier(val value: StyledElement.() -> Unit) : Modifier {
//
//    fun apply(element: StyledElement) {
//        ruleSet {
//            element.declarations
//        }
//    }
//}

enum class StyleKey(val value: String) {
    BACKGROUND_COLOR("background-color"), COLOR("color"), TEXT_TRANSFORM("text-transform"), LINE_HEIGHT("line-height"), MARGIN(
        "margin"
    ),
    PADDING(
        "padding"
    )
}

class ModifierImpl(classes: String) : Modifier {
    private var classList: MutableList<String?> = mutableListOf()
    var _styles: MutableList<Pair<StyleKey, Any>> = mutableListOf()

    init {
//        println("init")
        addClass(classes)
    }

    fun addStyle(name: StyleKey, value: Any): ModifierImpl {
        println("Adding style $name: $value")
        _styles.add(name to value)
        return this
    }

    fun addClass(value: String): ModifierImpl {
//        println("Adding class $value")
        classList.add(value)
        return this
    }

    internal val clazzes: String
        get() {
//            println("Getting classes: {${classList.size}}")
            return classList.joinToString(" ")
        }
//

}


