package lesson9_kt

import java.util.*
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask
import kotlin.math.max
import kotlin.random.Random
import kotlin.time.measureTime

class MyTask(val array: Array<Int>,
             val min : Int,
             val max : Int
): RecursiveTask<Int>()
{

    override fun compute(): Int {
        if (max - min > 25_000_000) {
            val mid = (max + min) / 2
            val task1 = MyTask(array, min, mid)
            val task2 = MyTask(array,mid + 1, max)

            task1.fork()
            task2.fork()
            return max(task1.get(), task2.get())
        } else {
            var res = array[min]
            for (i in min + 1 until max) {
                if (array[i] > max) {
                    res = array[i]
                }
            }
            return res
        }
    }
}

class Main {
    val array = Array<Int>(200_000_000) { _ -> Random.nextInt(0, 100_000)}

    fun case1() = measureTime {
        var max = array[0]
        for (i in 1 until array.size) {
            if (array[i] > max) {
                max = array[i]
            }
        }
        println(max)
    }

    fun case2() = measureTime {
        val max = array.max()
        println(max)
    }

    fun case3() = measureTime {
        val max = Arrays.stream(array).parallel().max{o1, o2 -> o1-o2}
        println(max)
    }

    fun case4() = measureTime {
        val max = ForkJoinPool.commonPool().invoke(MyTask(array, 0, array.size-1))
        println(max)
    }
}

fun main() {
    val obj = Main()
    println(obj.case1())
    println(obj.case2())
    println(obj.case3())
    println(obj.case4())
}