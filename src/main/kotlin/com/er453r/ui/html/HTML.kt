package com.er453r.ui.html

import com.er453r.ui.Property
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

fun createElement(name: String) = document.createElement(name) as HTMLElement

fun HTMLElement.div(classes: String? = null, block: HTMLElement.() -> Unit = {}) = element("div", classes, block)
fun HTMLElement.label(classes: String? = null, block: HTMLElement.() -> Unit = {}) = element("label", classes, block)
fun HTMLElement.checkbox(classes: String? = null, block: HTMLElement.() -> Unit = {}) = element("checkbox", classes, block)
fun HTMLElement.button(classes: String? = null, block: HTMLElement.() -> Unit = {}) = element("button", classes, block)

fun HTMLElement.h1(classes: String? = null, block: HTMLElement.() -> Unit = {}) = element("h1", classes, block)

fun HTMLElement.element(elementName: String, classes: String? = null, block: HTMLElement.() -> Unit = {}) {
    val element = createElement(elementName)

    classes?.let { element.className = it }

    element.apply(block)
    this.appendChild(element)
}

fun HTMLElement.text(text: String) {
    this.appendChild(document.createTextNode(text))
}

fun HTMLElement.click(block: Event.() -> Unit) {
    this.addEventListener("click", block)
}

fun HTMLElement.checkbox(property: Property<Boolean>, classes: String? = null, block: HTMLElement.() -> Unit = {}) = checkbox(classes, block)
