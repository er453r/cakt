import com.er453r.ui.UI
import kotlinx.css.*
import kotlinx.html.h1
import kotlinx.html.span

fun main() {
    object : UI("body") {
        override val root = html("div") {
            h1 {
                +"This is my first test!"
            }

            span {
                +"This is only a span"
            }
        }

        override val style = css {
            body {
                margin(0.px)

                fontSize = 2.em

                color = Color.gray
            }

            h1 {
                color = Color.red
            }
        }
    }
}
