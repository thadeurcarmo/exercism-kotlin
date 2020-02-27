object PrimeFactorCalculator {

    fun primeFactors(int: Int): List<Int> = primeFactors(int.toLong()).map(Long::toInt)

    fun primeFactors(long: Long): List<Long> = primeFactors(long, 2L, mutableListOf())

    private tailrec fun primeFactors(n: Long, currentFactor: Long, factors: List<Long>): List<Long> = when {
        n < 2 -> factors
        n < currentFactor * currentFactor -> factors + n
        n % currentFactor == 0L -> primeFactors(n / currentFactor, currentFactor, factors + currentFactor)
        else -> {
            val nextFactor = if (currentFactor == 2L) 3L else currentFactor + 2
            primeFactors(n, nextFactor, factors)
        }
    }
}
