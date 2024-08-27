package com.er453r.ca.spaces

import com.er453r.ca.Space
import com.er453r.utils.Vector

class Finite2dGridSpace<CELL>(private val width: Int, private val height: Int, private val static: Boolean = false, initializer: Finite2dGridSpace<CELL>.() -> CELL) : Space<CELL>() {
    private val space: List<List<CELL>>

    init {
        space = mutableListOf()

        for (n in 0 until width) {
            val row = mutableListOf<CELL>()

            space.add(row)

            for (m in 0 until height)
                row.add(initializer())
        }
    }

    private val cache = HashMap<CELL, List<CELL>>()

    override fun cells() = space.flatten()

    private fun findCell(cell: CELL): Vector? {
        for (column in 0 until width)
            for (row in 0 until height)
                if (space[column][row] == cell)
                    return Vector(column, row)

        return null
    }

    private fun findNearest(cell: CELL): List<CELL> = findCell(cell)?.let {
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

    override fun neighbours(cell: CELL): List<CELL> {
        if (static) {
            cache[cell]?.let { return it }

            findNearest(cell).let {
                cache[cell] = it
                return it
            }
        } else
            return findNearest(cell)
    }
}
