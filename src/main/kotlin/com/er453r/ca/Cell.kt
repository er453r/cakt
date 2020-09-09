package com.er453r.ca

class Cell(private val space: Space) {
    private var state = 0
    private var tempState = 0

    fun process(){
        val neighbours = space.findNearestTo(this)

        val liveNeighbours = neighbours.count { it.state == 1 }

        tempState = when{
            state == 1 && (liveNeighbours == 2 || liveNeighbours == 3) -> 1
            state == 0 && liveNeighbours == 3 -> 1
            else -> 0
        }
    }

    fun step(){
        state = tempState
    }
}
