object Transpose {

    fun transpose(input: List<String>): List<String> {
        return if (input.isEmpty()) {
            emptyList()
        } else {
            val size = input.maxBy { it.length }!!.length
            val result = MutableList(size) { "" }
            for ((index, row) in input.withIndex()) {
                for ((rowIndex, rowElem) in row.withIndex()) {
                    result[rowIndex] = result[rowIndex].padEnd(index) + rowElem
                }
            }
            result
        }
    }
}
