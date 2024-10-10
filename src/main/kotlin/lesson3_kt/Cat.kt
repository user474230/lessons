package lesson3_kt

class Cat : Participian{
    companion object {
        val CAT_MAX_DISTANCE = 20
        val CAT_MAX_HEIGHT = 30
    }
    override fun run(distance: Int) : Boolean = performAction(
        value = distance,
        maxValue = CAT_MAX_DISTANCE,
        action = "пробежал",
        actor = "кот"
    )

    override fun jump(height: Int) : Boolean= performAction(
        value = height,
        maxValue= CAT_MAX_HEIGHT,
        action = "прыгнул",
        actor = "кот"
    )
}