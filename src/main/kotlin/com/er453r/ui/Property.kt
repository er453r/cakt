package com.er453r.ui

import kotlinx.html.*
import org.w3c.dom.HTMLInputElement

class Property<T>(initialValue: T) {
    private val listeners: MutableList<(T) -> Unit> = mutableListOf()

    var skipListener:(T) -> Unit = {}

    var value: T = initialValue
        get() {
            console.log("Getting value $field")

            return field
        }
        set(newValue) {
            console.log("Setting value $newValue, calling listeners")

            field = newValue

            console.log("Skip listener $skipListener")

            listeners.forEach {
                console.log("Loop listener $it")

                it(field)
            }

            skipListener = {}
        }

    operator fun plusAssign(newValue: T) {
        value = newValue
    }

    fun addListener(block: (T) -> Unit) = listeners.add(block)
}


fun INPUT.onChange(property: Property<Boolean>) {
    val listener:(Boolean) -> Unit = {
        console.log("Change listener $it")

//        this.checked = it
    }

    property.addListener(listener)

    this.consumer.onTagEvent()

    this.consumer.onTagEvent(this, "onchange") {
        val element = it.target as HTMLInputElement

        console.log("html element check ${element.checked}")

        property.skipListener = listener
        property.value = element.checked
    }
}

fun FlowOrInteractiveOrPhrasingContent.checkBox(property: Property<Boolean>) = checkBoxInput {
    onChange(property)
}
