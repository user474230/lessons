package lesson3_kt

data class JumpObstacle(val height: Int) : Obstacle {
    override fun tryPass(participian: Participian): Boolean = participian.jump(height)
}