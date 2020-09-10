package com.er453r.ca

class Space(private val width: Int, private val height: Int) {
    private val space = Array(width) { Array(height) { Cell(this) } }

    fun cells() = space.flatMap { it.asList() }

    fun findCell(cell: Cell): Vector? {
        for (column in 0..width)
            for (row in 0..height)
                if (space[column][row] == cell)
                    return Vector(column, row)

        return null
    }

    fun findNearestTo(cell: Cell): List<Cell> {
        return findCell(cell)?.let {
            listOf(
                    space[(width + it.x - 1) % width][(height + it.y - 1) % height],
                    space[(width + it.x + 0) % width][(height + it.y - 1) % height],
                    space[(width + it.x + 1) % width][(height + it.y - 1) % height],
                    space[(width + it.x - 1) % width][(height + it.y + 0) % height],
                    space[(width + it.x + 1) % width][(height + it.y + 0) % height],
                    space[(width + it.x - 1) % width][(height + it.y + 1) % height],
                    space[(width + it.x + 0) % width][(height + it.y + 1) % height],
                    space[(width + it.x + 1) % width][(height + it.y + 1) % height],
            )
        } ?: emptyList()
    }
}
