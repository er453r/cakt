package com.er453r.ui

import kotlinx.browser.document
import kotlinx.css.CSSBuilder
import kotlinx.html.HtmlBlockTag
import kotlinx.html.dom.append
import org.w3c.dom.COMPLETE
import org.w3c.dom.DocumentReadyState

abstract class UI(private val selector: String) {
    abstract val root: HtmlBlockTag.() -> Unit

    abstract val style: CSSBuilder.() -> Unit

    init {
        if (document.readyState == DocumentReadyState.COMPLETE) {
            console.log("Document already loaded, adding UI")

            add()
        } else {
            document.addEventListener("DOMContentLoaded", {
                console.log("Document loaded, starting UI")

                add()
            })
        }
    }

    private fun add() {
        document.querySelector(selector)?.append { root }

        console.log("Adding styles...")

        document.querySelector("style")!!.innerHTML += CSSBuilder().apply(style).toString()
    }

    fun UI.css(block: CSSBuilder.() -> Unit) = block
    fun UI.html(element: String, block: HtmlBlockTag.() -> Unit) = block
}
