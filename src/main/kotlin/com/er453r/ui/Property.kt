package com.er453r.ui

import org.w3c.dom.HTMLInputElement

class Property<T>(initialValue: T) {
    private val listeners: MutableList<PropertyListener<T>> = mutableListOf()

    var skipListener:PropertyListener<T>? = null

    var value: T = initialValue
        set(newValue) {
            field = newValue

            listeners.filter { it != skipListener }.forEach { it.block(field) }

            skipListener = null
        }

    operator fun plusAssign(newValue: T) {
        value = newValue
    }

    fun addListener(listenerHolder: PropertyListener<T>) = listeners.add(listenerHolder)
}

//fun INPUT.onChange(property: Property<Boolean>) {
//    this.consumer.onTagEvent(this, "ready") {
//        val element = it.target as HTMLInputElement
//
//        console.log("loaded dom for ${element.tagName}")
//    }
//
//    val listenerHolder = PropertyListener<Boolean>{
//        console.log("Change listener $it")
//
//        //this.checked = it
//    }
//
//    property.addListener(listenerHolder)
//
//    onReadyStateChangeFunction = {
//        console.log("ready state change!")
//    }
//
//    onLoadFunction = {
//        console.log("on load!")
//    }
//
//    this.consumer.onTagEvent(this, "onchange") {
//        val element = it.target as HTMLInputElement
//
//        console.log("html element check ${element.checked}")
//
//        property.skipListener = listenerHolder
//        property.value = element.checked
//    }
//}
//
//fun FlowOrInteractiveOrPhrasingContent.checkBox(property: Property<Boolean>) = checkBoxInput {
//    onChange(property)
//}
