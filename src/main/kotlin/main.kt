import com.er453r.ui.properties.Property
import com.er453r.ui.UI
import com.er453r.ui.properties.checkbox
import com.er453r.ui.html.*
import com.er453r.ui.properties.textinput
import kotlinx.css.*

fun main() {
    val checkedProperty = Property(false)
    val textProperty = Property("")

    object : UI("body") {
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
                                checkbox(checkedProperty)

                                text("Run")
                            }
                        }

                        div {
                            button {
                                text("Step")

                                click {
                                    console.log("Clicked step!")
                                }
                            }
                        }

                        div {
                            label {
                                checkbox(checkedProperty)

                                text("Render")
                            }
                        }

                        div{
                            textinput(textProperty)
                        }

                        div{
                            textinput(textProperty)
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

        init {
            console.log("Starting component")
        }
    }
}
