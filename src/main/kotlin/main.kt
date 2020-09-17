import com.er453r.test.Test
import kotlinx.browser.document

fun main() {
    document.addEventListener("DOMContentLoaded", {
        console.log("Document loaded, starting UI")

        Test()
    })
}
