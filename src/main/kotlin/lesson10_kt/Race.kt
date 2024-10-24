package lesson10_kt

import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier

class Race(vararg stages: Stage?) {
    val stages: List<Stage> = ArrayList(Arrays.asList(*stages))
    val cyclicBarrier = CyclicBarrier(COMPETITORS_COUNT)
    val countDownLatch = CountDownLatch(COMPETITORS_COUNT)

    fun begin() {
        println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!")
        val cars = arrayOfNulls<Car>(COMPETITORS_COUNT)
        for (i in cars.indices) {
            cars[i] = Car(this, 20 + (Math.random() * 10).toInt())
        }
        for (i in cars.indices) {
            Thread(cars[i]).start()
        }
        println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!")
        countDownLatch.await()
        println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!")
        val winner = cars.minBy { car -> car?.finishTime ?: Long.MAX_VALUE }
        println("Победитель - ${winner?.name}")
    }

    companion object {
        const val COMPETITORS_COUNT: Int = 4
    }
}