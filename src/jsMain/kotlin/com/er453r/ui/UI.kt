package com.er453r.ui

import kotlinx.browser.document
import kotlinx.coroutines.*
import kotlinx.css.CssBuilder
import org.w3c.dom.HTMLElement

abstract class UI(private val selector: String = "body") {
    abstract val root: HTMLElement

    open fun onInit() {}

    open val style: CssBuilder.() -> Unit = {}

    init {
        console.log("Starting UI")

        // let the child properties initialize
        CoroutineScope(Dispatchers.Default).launch {
            delay(1)
            add()
        }
    }

    private fun add() {
        document.querySelector(selector)?.append(root)

        console.log("Adding styles...")

        document.querySelector("style")!!.innerHTML += CssBuilder().apply(style).toString()

        onInit()
    }

    fun css(block: CssBuilder.() -> Unit) = block
    fun html(elementName: String = "div", block: HTMLElement.() -> Unit): HTMLElement {
        val element = document.createElement(elementName) as HTMLElement

        element.apply(block)

        return element
    }
}
