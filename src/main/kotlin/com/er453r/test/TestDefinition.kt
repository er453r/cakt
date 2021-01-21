package com.er453r.test

import com.er453r.ca.Cell
import com.er453r.ca.Space

class TestDefinition<T>(
    val name: String,
    val description: String,
    val space: Space<out Cell<T>>,
    val stateConverter:(T) -> Float,
)
