package lesson3_kt

fun main() {
    val participians = arrayOf(Human(), Cat(), Robot())
    val obstacles = arrayOf(RunObstacle(5), JumpObstacle(10), RunObstacle(15), JumpObstacle(35))

    for (participian in participians) {
        for (obstacle in obstacles) {
            if (!obstacle.tryPass(participian))
                break;
        }
    }
}