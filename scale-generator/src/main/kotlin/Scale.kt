class Scale(private val tonic: String) {

    private val sharps = setOf("C", "G", "D", "A", "a", "E", "B", "F#", "e", "b", "f#", "c#", "g#", "d#")

    private val halfStepFlat = mapOf(
            "C" to "Db",
            "Db" to "D",
            "D" to "Eb",
            "Eb" to "E",
            "E" to "F",
            "F" to "Gb",
            "Gb" to "G",
            "G" to "Ab",
            "Ab" to "A",
            "A" to "Bb",
            "Bb" to "B",
            "B" to "C"
    )

    private val halfStepSharp = mapOf(
            "C" to "C#",
            "C#" to "D",
            "D" to "D#",
            "D#" to "E",
            "E" to "F",
            "F" to "F#",
            "F#" to "G",
            "G" to "G#",
            "G#" to "A",
            "A" to "A#",
            "A#" to "B",
            "B" to "C"
    )

    fun chromatic(): List<String> = interval("mmmmmmmmmmmm")

    fun interval(intervals: String): List<String> {
        val tonicCap = tonic.capitalize()
        val result = mutableListOf(tonicCap)
        val map = when {
            sharps.contains(tonic) -> halfStepSharp
            else -> halfStepFlat
        }
        var curr = tonicCap
        for (interval in intervals) {
            curr = map.get(curr)!!
            if (interval == 'M' || interval == 'A') {
                curr = map.get(curr)!!
                if (interval == 'A') {
                    curr = map.get(curr)!!
                }
            }
            result.add(curr)
        }
        return result.dropLast(1)
    }

}
