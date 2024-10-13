package lesson5_kt

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
        list.addAll(box.list)
        box.list.clear()
    }

    fun compare(box: Box<out Fruit>): Boolean {
        return box.getWeight() == getWeight()
    }
}