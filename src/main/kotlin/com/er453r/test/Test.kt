package com.er453r.test

import com.er453r.ca.CA
import com.er453r.ca.Cell
import com.er453r.plot.Image
import com.er453r.plot.colormaps.Inferno
import com.er453r.ui.UI
import com.er453r.ui.html.*
import com.er453r.ui.properties.Property
import com.er453r.ui.properties.checkbox
import kotlinx.css.*

class Test : UI() {
    private val running = Property(false)
    private val render = Property(true)
    private val iteration = Property(0)
//    private val fpsView = Property("")

    override val root = html {
        div(classes = "container") {
            div(classes = "controls") {
                fieldset {
                    legend {
                        text("Controls")

                        style {
                            color = Color.darkRed
                        }
                    }

                    div {
                        label {
                            checkbox(running)

                            text("Run")
                        }
                    }

                    div {
                        button {
                            text("Step")

                            click {
                                step()
                            }
                        }
                    }

                    div {
                        label {
                            checkbox(render)

                            text("Render")
                        }
                    }
                }

                fieldset {
                    legend {
                        text("Stats")
                    }

                    div {
                        id = "fps"
                    }
                }
            }
            div(classes = "plot") {
                h1 {
                    text("Test")
                }
            }
        }
    }

    override val style = css {
        body {
            margin = "0"
            padding = "0"
        }

        rule(".container") {
            display = Display.flex
            minHeight = 100.vh
        }

        rule(".controls") {
            width = 40.pct
            backgroundColor = Color.lightGray
        }

        rule(".plot") {
            flexGrow = 2.0
            backgroundColor = Color.darkGray
        }
    }

    //    private val fps: FPS = FPS()
    private val ca: CA
//    private val output: Image
    private val cells: List<Cell>

    init {
        console.log("CA Started!")

        val width = 1 * 32
        val height = 1 * 32

        ca = CA(width, height)

        cells = ca.cells

        val mid = (width * height + width) / 2

        cells[mid - width - width - 1].state = 1
        cells[mid - width + 2].state = 1
        cells[mid].state = 1
        cells[mid + 1].state = 1
        cells[mid + 2].state = 1

//        output = Image(width, height, Inferno(), selector = ".plot")

//        output.generic(cells) { it.state.toFloat() }
    }

    private fun step() {
        ca.step()

        iteration.value++

//        if (render.value)
//            output.generic(cells) { it.state.toFloat() }
    }

//    private fun loop() {
//
//
//        fpsView.value = "FPS ${fps.fps.format(2)}"
//
//            GlobalScope.launch {
//                delay(1)
//                loop()
//            }
//
//    }

    fun Double.format(digits: Int): String = this.asDynamic().toFixed(digits) as String
}
