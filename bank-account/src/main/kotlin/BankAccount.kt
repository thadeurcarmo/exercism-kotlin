import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class BankAccount {
    private var internalBalance = 0L
    private var open = true
    private val lock = ReentrantLock()

    var balance: Long = 0
        private set
        get() = threadSafe {
            internalBalance
        }

    fun adjustBalance(amount: Long) = threadSafe {
        internalBalance += amount
    }

    fun close() = threadSafe {
        open = false
    }

    private inline fun <T> threadSafe(action: () -> T): T = lock.withLock {
        check (open)
        return action()
    }
}
