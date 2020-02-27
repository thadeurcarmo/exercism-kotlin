import Orientation.*

data class GridPosition(val x: Int, val y: Int) {
    fun advance(orientation: Orientation) = when (orientation) {
        NORTH -> GridPosition(x, y + 1)
        SOUTH -> GridPosition(x, y - 1)
        WEST -> GridPosition(x - 1, y)
        EAST -> GridPosition(x + 1, y)
    }
}
