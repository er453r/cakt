package com.er453r.ui.properties

import org.w3c.dom.HTMLElement

class Property<T>(initialValue: T) {
    private val listeners: MutableList<PropertyListener<T>> = mutableListOf()

    var skipElement: HTMLElement? = null

    var value: T = initialValue
        set(newValue) {
            field = newValue

            listeners.filter { skipElement != it.element }.forEach { it.listener(field) }

            skipElement = null
        }

    operator fun plusAssign(newValue: T) {
        value = newValue
    }

    fun addListener(listener: PropertyListener<T>) = listeners.add(listener)
}
