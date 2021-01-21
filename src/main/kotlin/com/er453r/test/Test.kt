package com.er453r.test

import com.er453r.ca.CA
import com.er453r.ca.implementations.gameoflife.GameOfLifeCell
import com.er453r.ca.implementations.random.RandomCell
import com.er453r.ca.spaces.Finite2dGridSpace
import com.er453r.plot.Image
import com.er453r.plot.colormaps.Inferno
import com.er453r.ui.UI
import com.er453r.ui.html.*
import com.er453r.ui.properties.Property
import com.er453r.ui.properties.checkbox
import com.er453r.ui.properties.select
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

    private val typeSelected = Property("")

    private val width = Property(32)
    private val height = Property(32)

    private val tests = mapOf(
        "gameOfLife" to TestDefinition(
            name = "Game of life",
            description = "Classic game of life",
            space = Finite2dGridSpace<GameOfLifeCell>(width.value, height.value, static = true) {
                GameOfLifeCell(0, this)
            },
            stateConverter = { it.toFloat() },
        ),
        "random" to TestDefinition(
            name = "Random",
            description = "Random values, just showing of the UI",
            space = Finite2dGridSpace<RandomCell>(width.value, height.value, static = true) {
                RandomCell()
            },
            stateConverter = { it.toFloat() },
        ),
    )

    override val root = html {
        div(classes = "container") {
            div(classes = "controls") {
                fieldset {
                    legend {
                        text("Controls")
                    }

                    div {
                        select(typeSelected) {
                            for (test in tests.entries) {
                                option(test.key) {
                                    text(test.value.name)
                                }
                            }
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
    private var ca: CA<*>? = null
    private var output: Image? = null

    override fun onInit() {
        console.log("CA Started!")

        val width = this.width.value
        val height = this.height.value

        output = Image(width, height, Inferno(), selector = ".plot")

        running.onChange {
            if (it) loop()
        }

        typeSelected.onChange {
            console.log("Changed value to $it")

            init(it)
        }
    }

    var update: (() -> Unit)? = null

    private fun init(id: String) {
        console.log("Loading $id...")

        iteration.value = 0

        tests[id]?.let { test ->
            ca = CA(test.space)

            update = {
                val cells = test.space.cells()
                val values = cells.map { test.stateConverter(it.state) }

                output?.generic(values) { it }
            }
        }

//        if(id == "gameOfLife"){
//            val width = this.width.value
//            val height = this.height.value
//
//            val mid = (width * height + width) / 2
//
//            cells[mid - width - width - 1].state = 1
//            cells[mid - width + 2].state = 1
//            cells[mid].state = 1
//            cells[mid + 1].state = 1
//            cells[mid + 2].state = 1
//        }
    }

    private fun step() {
        ca?.let {
            it.step()

            iteration.value++

            if (render.value)
                update?.invoke()
        }
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
