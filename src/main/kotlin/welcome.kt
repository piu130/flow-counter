import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div

fun <T> useStateFlow(flow: StateFlow<T>): T {
    val (state, setState) = useState(flow.value)

    useEffectWithCleanup(listOf()) {
        val job = flow.onEach { setState(it) }.launchIn(GlobalScope)
        return@useEffectWithCleanup { job.cancel() }
    }
    
    return state
}

object CounterStore {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> get() = _counter
    
    fun inc() { _counter.value++ }
}

fun RBuilder.welcome(handler: RProps.() -> Unit) = child(welcome) { attrs { handler() } }
val welcome = functionalComponent<RProps> {
    val counter = useStateFlow(CounterStore.counter)

    div {
        +"Counter: $counter"
    }
    button {
        attrs.onClickFunction = { CounterStore.inc() }
        +"Increment"
    }
}
