package com.er453r.ca

abstract class Space<CELL> {
    abstract fun cells():List<CELL>
    abstract fun neighbours(cell:CELL):List<CELL>
}
