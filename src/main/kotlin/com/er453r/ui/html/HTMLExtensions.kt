package com.er453r.ui.html

import kotlinx.browser.document
import kotlinx.css.CSSBuilder
import org.w3c.dom.*
import org.w3c.dom.events.Event

fun HTMLElement.div(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLDivElement.() -> Unit = {}) = element("div", classes, attributes, block)
fun HTMLElement.label(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLLabelElement.() -> Unit = {}) = element("label", classes, attributes, block)
fun HTMLElement.input(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLInputElement.() -> Unit = {}) = element("input", classes, attributes, block)
fun HTMLElement.button(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLButtonElement.() -> Unit = {}) = element("button", classes, attributes, block)

fun HTMLElement.fieldset(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLFieldSetElement.() -> Unit = {}) = element("fieldset", classes, attributes, block)
fun HTMLElement.legend(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLLegendElement.() -> Unit = {}) = element("legend", classes, attributes, block)

fun HTMLElement.checkbox(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLInputElement.() -> Unit = {}) = input(classes, attributes.extend(mapOf("type" to "checkbox")), block)
fun HTMLElement.textinput(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLInputElement.() -> Unit = {}) = input(classes, attributes.extend(mapOf("type" to "text")), block)
fun HTMLElement.select2(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLSelectElement.() -> Unit = {}) = element("select", classes, attributes, block)
fun HTMLElement.option(value:String, classes: String? = null, attributes: Map<String, String>? = null, block: HTMLInputElement.() -> Unit = {}) = element("option", classes, attributes.extend(mapOf("value" to value)), block)

fun Map<String, String>?.extend(extends: Map<String, String>) = (this?.toMutableMap() ?: mutableMapOf()).apply { putAll(extends) }

fun HTMLElement.h1(classes: String? = null, attributes: Map<String, String>? = null, block: HTMLHeadingElement.() -> Unit = {}) = element("h1", classes, attributes, block)

fun <T : HTMLElement> HTMLElement.element(elementName: String, classes: String? = null, attributes: Map<String, String>? = null, block: T.() -> Unit = {}):T {
    val element = document.createElement(elementName).unsafeCast<T>()

    val attrs = classes?.let { attributes.extend(mapOf("class" to it)) } ?: attributes

    attrs?.entries?.forEach { element.setAttribute(it.key, it.value) }

    element.apply(block)
    this.appendChild(element)

    return element
}

fun HTMLElement.text(text: String) {
    this.appendChild(document.createTextNode(text))
}

fun HTMLElement.click(block: Event.() -> Unit) {
    this.addEventListener("click", block)
}

fun HTMLElement.style(block: CSSBuilder.() -> Unit){
    this.setAttribute("style", CSSBuilder().apply(block).toString())
}
