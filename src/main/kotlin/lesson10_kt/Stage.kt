package lesson10_kt

abstract class Stage {
    var length: Int = 0
    var description: String? = null

    abstract fun overcome(c: Car)
}