object WordCount {
    fun phrase(phrase: String): Map<String, Int> {
        return Regex("""\w+'\w+|\b\w+\b""")
                .findAll(phrase)
                .groupingBy { it.value.toLowerCase() }.eachCount()
    }
}
