package lesson3_kt

class Robot : Participian{
    companion object {
        val ROBOT_MAX_DISTANCE = 30
        val ROBOT_MAX_HEIGHT = 40
    }
    override fun run(distance: Int) : Boolean = performAction(
        value = distance,
        maxValue = ROBOT_MAX_DISTANCE,
        action = "пробежал",
        actor = "робот"
    )

    override fun jump(height: Int) : Boolean= performAction(
        value = height,
        maxValue= ROBOT_MAX_HEIGHT,
        action = "прыгнул",
        actor = "робот"
    )
}