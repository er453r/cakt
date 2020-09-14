package com.er453r.ui

import kotlinx.browser.document
import kotlinx.css.CSSBuilder
import kotlinx.html.BUTTON
import kotlinx.html.INPUT
import kotlinx.html.TagConsumer
import kotlinx.html.dom.append
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.COMPLETE
import org.w3c.dom.DocumentReadyState
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

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

    fun BUTTON.click(block: Event.() -> Unit) {
        this.onClickFunction = {
            it.apply(block)
        }
    }

    fun INPUT.change(block: Event.(Boolean) -> Unit) {
        onChangeFunction = {
            val element = it.target as HTMLInputElement

            it.apply { block(element.checked) }
        }
    }
}
