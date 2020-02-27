class Series(private val sequence: String) {

    init {
        require(sequence.matches(regex = Regex("""\d*"""))) {
            "Sequence must contain only numbers"
        }
    }

    fun getLargestProduct(span: Int): Long {
        require(span >= 0 && span <= sequence.length) {
            "Span must be in the range of 0 to ${sequence.length}"
        }
        return if (span == 0 || sequence.isEmpty()) 1
        else sequence.windowed(span).map { numbers ->
            numbers.fold(1L) { acc, digit -> acc * digit.toString().toLong() }
        }.max()!!
    }
}
