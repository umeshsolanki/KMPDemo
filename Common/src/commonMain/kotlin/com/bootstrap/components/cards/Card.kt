package com.bootstrap.components.cards

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.classes
import com.bootstrap.modifier.style
import kotlinx.html.*

inline fun FlowContent.Card(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    image: String? = null,
    crossinline actions: DIV.() -> Unit
) {
    div("card ${modifier.classes}") {
        modifier.style?.let {
            style = it
        }
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