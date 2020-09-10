package com.er453r.ca

import com.er453r.plot.Image
import com.er453r.plot.colormaps.Inferno
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import kotlin.js.Date

class Test {
    private val fps: FPS = FPS()

    private val ca: CA
    private var past: Float
    private val stats: Element

    private var iter: Int = 0

    private val output: Image

    private val cells:List<Cell>

    val render:HTMLInputElement

    init {
        console.log("CA Started!")

        stats = document.getElementById("fps")!!

        render = (document.getElementById("render") as HTMLInputElement?)!!

        val width = 3 * 32
        val height = 3 * 32

        ca = CA(width, height)

        cells = ca.cells

        val mid = (width * height + width) / 2

        cells[mid - width - width - 1].state = 1
        cells[mid - width + 2].state = 1
        cells[mid].state = 1
        cells[mid + 1].state = 1
        cells[mid + 2].state = 1

        output = Image(width, height, Inferno())

        past = Date.now().toFloat()

        output.generic(cells) { it.state.toFloat() }

        loop()
    }

    private fun loop() {
        println("Step!")

        ca.step()

        if(render.checked)
            output.generic(cells) { it.state.toFloat() }

        iter++

        if (iter % 1 == 0)
            stats.innerHTML = "FPS ${fps.update().format(2)}"
        else
            fps.update()

        if (iter < 1000)
            GlobalScope.launch {
                delay(1)
                loop()
            }
        else {
            val now: Float = Date.now().toFloat()

            val diff: Float = (now - past) / 1e3f

            println("Done in $diff")
        }
    }

    fun Double.format(digits: Int): String = this.asDynamic().toFixed(digits) as String
}
