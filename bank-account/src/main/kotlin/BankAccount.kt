import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class BankAccount {
    private var internalBalance = 0L
    private var closed = false
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
        closed = true
    }

    private inline fun <T> threadSafe(action: () -> T): T = lock.withLock {
        if (closed) {
            throw IllegalStateException("account is closed")
        }
        return action()
    }
}
