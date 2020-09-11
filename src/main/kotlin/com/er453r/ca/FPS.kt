package com.er453r.ca

import kotlin.js.Date

class FPS {
    private var frames: Int = 0

    var fps: Double = 0.0

    private var past: Double = Date.now()

    fun update() {
        frames++

        val now = Date.now()

        val diff = (now - past) / 1000

        if (diff > 1.0) {
            fps = frames / diff
            past = now
            frames = 0
        }
    }
}
