package com.er453r.ca

class CA {
    private val cells:List<Cell> = emptyList()

    fun step(){
        cells.forEach {
            it.process()
        }

        cells.forEach {
            it.step()
        }
    }
}
