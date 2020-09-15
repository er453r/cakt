import com.er453r.ui.Property
import com.er453r.ui.UI
import com.er453r.ui.checkBox
import kotlinx.css.*
import kotlinx.html.*

fun main() {
    val checkedProperty = Property(false)

    object : UI("body") {
        override val root = html {
            div(classes = "container") {
                div(classes = "controls") {
                    div {
                        label {
                            checkBox(checkedProperty)

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
                            checkBox(checkedProperty)

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
