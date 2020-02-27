class Robot(initGridPosition: GridPosition = GridPosition(x = 0, y = 0), initOrientation: Orientation = Orientation.NORTH) {

    var gridPosition: GridPosition
        private set

    var orientation: Orientation
        private set

    init {
        this.gridPosition = initGridPosition
        this.orientation = initOrientation
    }

    fun simulate(instructions: String) {
        for (instruction in instructions) {
            when (instruction) {
                'R' -> orientation = orientation.right()
                'L' -> orientation = orientation.left()
                'A' -> gridPosition = gridPosition.advance(orientation)
                else -> TODO("instruction $instruction is not supported")
            }
        }
    }
}
