package com.er453r.ca

abstract class Cell<STATE>(var state: STATE) {
    var nextState:STATE = state

    abstract fun process()

    fun step(){
        state = nextState
    }
}
