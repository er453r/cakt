package com.er453r.test

import com.er453r.ca.implementations.gameoflife.gameOfLife
import com.er453r.plot.Image
import com.er453r.plot.colormaps.Inferno
import com.er453r.ui.UI
import com.er453r.ui.html.*
import com.er453r.ui.properties.Property
import com.er453r.ui.properties.checkbox
import com.er453r.utils.FPS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.css.*

class Test : UI() {
    private val running = Property(false)
    private val render = Property(true)
    private val iteration = Property(0)
    private val fpsView = Property("")

    private val width = Property(128)
    private val height = Property(128)

    override val root = html {
        div(classes = "container") {
            div(classes = "controls") {
                fieldset {
                    legend {
                        text("Controls")
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

            div(classes = "plot-container") {
                fieldset {
                    legend {
                        text("Plot")
                    }

                    div(classes = "plot") {

                    }
                }
            }
        }
    }

    override val style = css {
        body {
            margin(1.em)
            padding(1.em)
            backgroundColor = Color("#101010")
            borderColor = Color.whiteSmoke
            color = Color.whiteSmoke
            fontFamily = "Verdana, Geneva, sans-serif"
        }

        rule(".container") {
            display = Display.flex
            minHeight = 100.vh
        }

        rule(".controls") {
            width = 40.pct
        }

        rule(".plot-container") {
            flexGrow = 2.0
        }
    }

    private val fpsCounter: FPS = FPS()
    private val ca = gameOfLife(width.value, height.value)
    private var output: Image? = null
    private val cells = ca.space.cells()

    override fun onInit() {
        console.log("CA Started!")

        val width = this.width.value
        val height = this.height.value

        val mid = (width * height + width) / 2

        cells[mid - width - width - 1].state = 1
        cells[mid - width + 2].state = 1
        cells[mid].state = 1
        cells[mid + 1].state = 1
        cells[mid + 2].state = 1

        output = Image(width, height, Inferno(), selector = ".plot")

        output?.generic(cells) { it.state.toFloat() }

        running.onChange {
            if (it) loop()
        }
    }

    private fun step() {
        ca.step()

        iteration.value++

        if (render.value)
            output?.generic(cells) { it.state.toFloat() }
    }

    private fun loop() {
        step()

        fpsView.value = "FPS ${fpsCounter.fps.format(2)}"

        if (running.value) {
            GlobalScope.launch {
                delay(1)
                loop()
            }
        }
    }

    fun Double.format(digits: Int): String = this.asDynamic().toFixed(digits) as String
}
