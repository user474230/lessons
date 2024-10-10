package lesson3_kt

data class RunObstacle(val distance: Int) : Obstacle {
    override fun tryPass(participian: Participian): Boolean = participian.run(distance)
}