import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input

interface WelcomeProps : RProps {
    var name: String
}

fun RBuilder.welcome(handler: WelcomeProps.() -> Unit) = child(welcome) { attrs { handler() } }
val welcome = functionalComponent<WelcomeProps> {
    val (name, setName) = useState(it.name)

    div {
        +"Hello, $name"
    }
    input {
        attrs {
            type = InputType.text
            value = name
            onChangeFunction = { event ->
                setName(
                    (event.target as HTMLInputElement).value
                )
            }
        }
    }
}
