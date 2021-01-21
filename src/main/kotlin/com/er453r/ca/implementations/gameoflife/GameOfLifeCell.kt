package com.er453r.ca.implementations.gameoflife

import com.er453r.ca.Cell
import com.er453r.ca.Space

class GameOfLifeCell(state: Int, private val space: Space<GameOfLifeCell>) : Cell<Int>(state) {
    override fun process() {
        val neighbours = space.neighbours(this)

        val liveNeighbours = neighbours.count { it.state == 1 }

        nextState = when {
            state == 1 && (liveNeighbours == 2 || liveNeighbours == 3) -> 1
            state == 0 && liveNeighbours == 3 -> 1
            else -> 0
        }
    }
}
