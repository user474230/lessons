package lesson5_kt

import kotlin.collections.ArrayList

fun main() {
//    val array = arrayOf(1,2,3,4,5,6)
//    case1(array, 0, 3)
//    println(Arrays.toString(array))

//    val array = arrayOf(1,2,3,4,5,6)
//    val list = case2(array)
//    println(list.toString())

    val box1 = Box<Apple>()
        .add(Apple())
        .add(Apple())
        .add(Apple())
    val box2 = Box<Apple>()
        .add(Apple())
        .add(Apple())
        .add(Apple())
    val box3 = Box<Orange>()
        .add(Orange())
        .add(Orange())
    val box4 = Box<Orange>()
        .add(Orange())
        .add(Orange())
    val box5 = Box<Orange>()
        .add(Orange())

    println(box2.compare(box1))
    println(box3.compare(box1))
    println(box2.compare(box5))

    box1.move(box2)
    box3.move(box4)
    println(box1.compare(box3))
    println(box2.compare(box4))
}

fun <T> case1(array: Array<T>, ind1: Int, ind2: Int) {
    val tmp = array[ind1]
    array[ind1] = array[ind2]
    array[ind2] = tmp
}

fun <T> case2(array: Array<T>): ArrayList<T> {
    val res = ArrayList<T>()
    for (item in array) {
        res.add(item)
    }
    return res
}

