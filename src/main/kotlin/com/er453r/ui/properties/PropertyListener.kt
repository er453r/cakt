package com.er453r.ui.properties

import org.w3c.dom.HTMLElement

class PropertyListener<T>(val element:HTMLElement, val listener: (T) -> Unit)
