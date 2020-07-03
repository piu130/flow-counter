import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div

object CounterStore {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> get() = _counter
    
    fun inc() { _counter.value++ }
}

fun RBuilder.welcome(handler: RProps.() -> Unit) = child(welcome) { attrs { handler() } }
val welcome = functionalComponent<RProps> {
    val (counter, setCounter) = useState(CounterStore.counter.value)

    useEffectWithCleanup(listOf()) {
        val job = CounterStore.counter.onEach { setCounter(it) }.launchIn(GlobalScope)
        return@useEffectWithCleanup { job.cancel() }
    }

    div {
        +"Counter: $counter"
    }
    button {
        attrs.onClickFunction = { CounterStore.inc() }
        +"Increment"
    }
}
