package com.er453r.ui

import kotlinx.browser.document
import kotlinx.css.CSSBuilder
import kotlinx.html.TagConsumer
import kotlinx.html.dom.append
import org.w3c.dom.COMPLETE
import org.w3c.dom.DocumentReadyState
import org.w3c.dom.HTMLElement

abstract class UI(private val selector: String = "body") {
    abstract val root: TagConsumer<HTMLElement>.() -> Unit

    open val style: CSSBuilder.() -> Unit = {}

    init {
        console.log("Starting UI")

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
        document.querySelector(selector)?.append { root() }

        console.log("Adding styles...")

        document.querySelector("style")!!.innerHTML += CSSBuilder().apply(style).toString()
    }

    fun css(block: CSSBuilder.() -> Unit) = block
    fun html(block: TagConsumer<HTMLElement>.() -> Unit) = block
}
