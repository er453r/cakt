import com.er453r.ui.UI
import kotlinx.css.*
import kotlinx.html.*

fun main() {
    object : UI("body") {
        override val root = html {
            div(classes = "container") {
                div(classes = "controls") {
                    div {
                        label {
                            checkBoxInput {
                                change {
                                    console.log("Run changed to ${it}!")
                                }
                            }

                            +"Run"
                        }
                    }

                    div {
                        button {
                            +"Step"

                            click {
                                console.log("Clicked step!")
                            }
                        }
                    }

                    div {
                        label {
                            checkBoxInput {
                                change {
                                    console.log("Render changed to ${it}!")
                                }
                            }

                            +"Render"
                        }
                    }

                    div {
                        id = "fps"
                    }
                }
                div(classes = "plot") {
                    h1 {
                        +"Test"
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
