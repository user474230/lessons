package lesson3_kt

class Human : Participian{
    companion object {
        val HUMAN_MAX_DISTANCE = 10
        val HUMAN_MAX_HEIGHT = 20
    }
    override fun run(distance: Int) : Boolean = performAction(
        value = distance,
        maxValue = HUMAN_MAX_DISTANCE,
        action = "пробежал",
        actor = "человек"
    )

    override fun jump(height: Int) : Boolean= performAction(
        value = height,
        maxValue= HUMAN_MAX_HEIGHT,
        action = "прыгнул",
        actor = "человек"
    )
}