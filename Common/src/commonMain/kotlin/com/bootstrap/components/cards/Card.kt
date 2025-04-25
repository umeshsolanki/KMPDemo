package com.bootstrap.components.cards

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.applyAttrFromModifier
import com.bootstrap.modifier.classes
import kotlinx.html.*

inline fun FlowContent.Card(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    image: String? = null,
    crossinline actions: DIV.() -> Unit
) {
    div("card ${modifier.classes}") {
        applyAttrFromModifier(modifier)
        image?.let { img ->
            div("card-img-top") {
                img(src = img) {}
            }
        }
        div("card-body") {
            title?.let {
                h5("card-title") {
                    +it
                }
            }
            subtitle?.let {
                p("card-text") {
                    +it
                }
            }
            actions()
        }
    }
}