package com.er453r.ui.html

import com.er453r.ui.Property
import kotlinx.browser.document
import org.w3c.dom.*
import org.w3c.dom.events.Event

fun HTMLElement.div(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLDivElement.() -> Unit = {}) = element("div", classes, attributes, block)
fun HTMLElement.label(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLLabelElement.() -> Unit = {}) = element("label", classes, attributes, block)
fun HTMLElement.input(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLInputElement.() -> Unit = {}) = element("input", classes, attributes, block)
fun HTMLElement.button(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLButtonElement.() -> Unit = {}) = element("button", classes, attributes, block)

fun HTMLElement.checkbox(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLInputElement.() -> Unit = {}) = input(classes, attributes.extend(mapOf("type" to "checkbox")), block)

fun Map<String, String>?.extend(extends: Map<String, String>) = (this?.toMutableMap() ?: mutableMapOf()).apply { putAll(extends) }

fun HTMLElement.h1(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLHeadingElement.() -> Unit = {}) = element("h1", classes, attributes, block)

fun <T : HTMLElement> HTMLElement.element(elementName: String, classes: String? = null, attributes: Map<String, String>? = null, block: T.() -> Unit = {}) {
    val element = document.createElement(elementName).unsafeCast<T>()

    val attrs = classes?.let { attributes.extend(mapOf("class" to it)) } ?: attributes

    attrs?.entries?.forEach { element.setAttribute(it.key, it.value) }

    element.apply(block)
    this.appendChild(element)
}

fun HTMLElement.text(text: String) {
    this.appendChild(document.createTextNode(text))
}

fun HTMLElement.click(block: Event.() -> Unit) {
    this.addEventListener("click", block)
}

fun HTMLElement.checkbox(property: Property<Boolean>, classes: String? = null, attributes: Map<String, String>? = null, block: HTMLElement.() -> Unit = {}) = checkbox(classes, attributes, block)
