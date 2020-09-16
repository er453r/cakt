package com.er453r.ui.properties

import com.er453r.ui.html.checkbox
import com.er453r.ui.html.textinput
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

fun HTMLInputElement.onChecked(property: Property<Boolean>) {
    val listener = PropertyListener<Boolean>(this) { this.checked = it }

    property.addListener(listener)

    addEventListener("change", {
        property.skipElement = this
        property.value = this.checked
    })
}

fun HTMLInputElement.onChange(property: Property<String>) {
    val listener = PropertyListener<String>(this) { this.value = it }

    property.addListener(listener)

    val changeListener: (Event) -> Unit = {
        property.skipElement = this

        property.value = this.value
    }

    addEventListener("keyup", changeListener)
    addEventListener("change", changeListener)
}

fun HTMLElement.checkbox(property: Property<Boolean>) = checkbox {
    onChecked(property)
}

fun HTMLElement.textinput(property: Property<String>) = textinput {
    onChange(property)
}
