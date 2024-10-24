package lesson5_kt

import kotlin.math.abs

class Box<T : Fruit> {
    val list = ArrayList<T>()

    fun getWeight(): Float {
        var sum = 0.0f
        list.forEach { it -> sum += it.getSingleWeight() }
        return sum
    }

    fun add(item: T): Box<T> {
        list.add(item)
        return this
    }

    fun move(box: Box<T>) {
        if (box == this) {
            return
        }
        list.addAll(box.list)
        box.list.clear()
    }

    fun compare(box: Box<out Fruit>): Boolean {
        return abs(box.getWeight() - getWeight()) < 0.00001f
    }
}