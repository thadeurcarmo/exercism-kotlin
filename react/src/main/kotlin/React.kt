import kotlin.properties.Delegates.observable

class Reactor<T>() {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    interface Subscription {
        fun cancel()
    }

    abstract inner class Cell {
        abstract val value: T
        protected val dependents = mutableListOf<ComputeCell>()

        fun registerObserver(observer: ComputeCell) = dependents.add(observer)
    }

    inner class InputCell(initialValue: T) : Cell() {
        // this is way more elegant than explicitly working with the setter
        override var value by observable(initialValue) { _, _, _ ->
            // first make sure we update the graph of values correctly
            dependents.forEach { it.refValueChanged() }
            // so we can process the callback notification properly
            dependents.forEach { it.notifyCallbacks() }
        }
    }

    inner class ComputeCell(private val computation: () -> T) : Cell() {
        override var value = computation()
            private set

        private val callbacks = mutableSetOf<(T) -> Any>()

        private var lastNotifiedValue = value

        constructor(vararg cells: Cell, computation: (List<T>) -> T) :
                this({ computation(cells.map { it.value }) }) {
            cells.forEach { it.registerObserver(this) }
        }

        internal fun refValueChanged(): Unit {
            val newValue = computation()
            if (value != newValue) {
                value = newValue
                dependents.forEach { it.refValueChanged() }
            }
        }

        internal fun notifyCallbacks() {
            if (value != lastNotifiedValue) {
                lastNotifiedValue = value
                callbacks.forEach { it(lastNotifiedValue) }
            }
            dependents.forEach { it.notifyCallbacks() }
        }

        fun addCallback(callback: (T) -> Any): Subscription {
            callbacks.add(callback)
            return object : Subscription {
                override fun cancel() {
                    callbacks.remove(callback)
                }
            }
        }
    }
}
