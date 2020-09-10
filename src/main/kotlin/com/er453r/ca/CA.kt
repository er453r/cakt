package com.er453r.ca

class CA(width:Int, height:Int) {
    private val space = Space(width, height)
    val cells = space.cells()

    fun step(){
        cells.forEach {
            it.process()
        }

        cells.forEach {
            it.step()
        }
    }
}
