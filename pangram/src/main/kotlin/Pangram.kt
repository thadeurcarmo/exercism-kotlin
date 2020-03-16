object Pangram {

    private const val EXPECTED_LETTER_COUNT = 26

    fun isPangram(input: String): Boolean = input
            .filter(Char::isLetter)
            .groupBy(Char::toLowerCase).size == EXPECTED_LETTER_COUNT
}
