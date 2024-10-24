package lesson10_kt

import java.util.concurrent.Semaphore

class Tunnel : Stage() {
    init {
        this.length = 80
        this.description = "Тоннель $length метров"
    }

    val semaphore =  Semaphore(2)

    override fun overcome(c: Car) {
        try {
            try {
                println(c.name + " готовится к этапу(ждет): " + description)
                semaphore.acquire()
                println(c.name + " начал этап: " + description)
                Thread.sleep((length / c.speed * 1000).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                println(c.name + " закончил этап: " + description)
                semaphore.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}