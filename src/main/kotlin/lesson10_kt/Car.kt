package lesson10_kt

class Car(private val race: Race, val speed: Int) : Runnable {
    val name: String
    var finishTime = 0L

    init {
        CARS_COUNT++
        this.name = "Участник #" + CARS_COUNT
    }

    override fun run() {
        try {
            println(this.name + " готовится")
            Thread.sleep((500 + (Math.random() * 800).toInt()).toLong())
            println(this.name + " готов")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        race.cyclicBarrier.await()
        for (i in race.stages.indices) {
            race.stages[i].overcome(this)
        }
        finishTime = System.currentTimeMillis()
        race.countDownLatch.countDown()
    }

    companion object {
        private var CARS_COUNT = 0
    }
}