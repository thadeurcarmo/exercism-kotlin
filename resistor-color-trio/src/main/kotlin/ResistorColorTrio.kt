import kotlin.math.pow

object ResistorColorTrio {

    fun text(vararg input: Color): String {
        require(input.size == 3) { "input must have 3 colors but has ${input.size}" }
        val firstBand = input[0].ordinal * 10
        val secondBand = input[1].ordinal
        val multiplier = 10.0.pow(input[2].ordinal).toInt()
        val resistorValue = (firstBand + secondBand) * multiplier
        return format(resistorValue)
    }

    private tailrec fun format(resistorValue: Int, currentPos: Int = 0): String {
        return if (resistorValue < 1000) "$resistorValue ${Unit.values()[currentPos].name.toLowerCase()}"
        else format(resistorValue / 1000, currentPos + 1)
    }
}

