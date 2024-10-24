package lesson10_kt

class Road(length: Int) : Stage() {
    init {
        this.length = length
        this.description = "Дорога $length метров"
    }

    override fun overcome(c: Car) {
        try {
            println(c.name + " начал этап: " + description)
            Thread.sleep((length / c.speed * 1000).toLong())
            println(c.name + " закончил этап: " + description)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}