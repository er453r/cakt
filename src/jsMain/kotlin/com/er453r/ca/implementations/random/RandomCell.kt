package com.er453r.ca.implementations.random

import com.er453r.ca.Cell
import kotlin.random.Random

class RandomCell : Cell<Int>(0) {
    override fun process() {
        nextState = Random.nextInt() % 2
    }
}
