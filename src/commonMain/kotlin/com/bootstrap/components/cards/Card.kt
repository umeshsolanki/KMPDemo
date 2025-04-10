package com.bootstrap.components.cards

import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.classes
import kotlinx.html.*

inline fun FlowContent.Card(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    image: String? = null,
    crossinline actions: DIV.() -> Unit
) {
    div("card ${modifier.classes}") {
        image?.let { img ->
            div("card-img-top") {
                img(src = img) {}
            }
        }
        div("card-body") {
            h5("card-title") {
                +title
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