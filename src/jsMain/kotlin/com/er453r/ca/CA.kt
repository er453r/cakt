package com.er453r.ca

class CA<SPACE:Space<out Cell<*>>>(private val space:SPACE) {
    fun step(){
        val cells = space.cells()

        cells.forEach {
            it.process()
        }

        cells.forEach {
            it.step()
        }
    }
}
