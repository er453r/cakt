package com.er453r.ca.implementations.gameoflife

import com.er453r.ca.CA
import com.er453r.ca.spaces.Finite2dGridSpace

fun gameOfLife(width:Int, height:Int):CA<Finite2dGridSpace<GameOfLifeCell>>{
    val space = Finite2dGridSpace<GameOfLifeCell>(width, height, static = true){
        GameOfLifeCell(0, this)
    }

    return CA(space)
}
