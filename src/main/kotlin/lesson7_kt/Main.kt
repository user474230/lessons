package lesson7_kt

import java.util.LinkedList
import kotlin.random.Random


fun main() {
    case1()
}

//ArrayList:     1       1        1        1        1
//LinkedList:    6      24      474     4998    50697
fun case1() {
    val arrayLists = Array(5) { ArrayList<Int>() }
    val linkedList = Array(5) { LinkedList<Int>() }

    var size = 1
    for (list in arrayLists) {
        size *= 10
        list.addAll(0 until size)
    }

    size = 1
    for (list in linkedList) {
        size *= 10
        list.addAll(0 until size)
    }

    val terser = { list : MutableList<Int> ->
        val t1 = System.currentTimeMillis()
        for (i in 0 until 1_000_000) {
            var value = list[list.size/2]
            value += 10
        }
        val t2 = System.currentTimeMillis()
        println(t2-t1)
    }

    for (list in arrayLists) {
        terser(list)
    }

    for (list in linkedList) {
        terser(list)
    }
}

//ArrayList:    1       1       1       1       68
//LinkedList:   1       1       1      21     1947
fun case2() {
    val arrayLists = Array(5) { ArrayList<Int>() }
    val linkedList = Array(5) { LinkedList<Int>() }

    var size = 1
    for (list in arrayLists) {
        size *= 10
        list.addAll(0 until size)
    }

    size = 1
    for (list in linkedList) {
        size *= 10
        list.addAll(0 until size)
    }

    val terser = { list : MutableList<Int> ->
        val t1 = System.currentTimeMillis()
        val size = list.size
        for (i in 0 until size / 2) {
            list.removeAt(list.size/2)
        }
        val t2 = System.currentTimeMillis()
        println(t2-t1)
    }

    for (list in arrayLists) {
        terser(list)
    }

    for (list in linkedList) {
        terser(list)
    }
}

//ArrayList<MyEntry> : 5715
//HashMap<Int, Int>  : 6
fun case3() {
    data class MyEntry (
        val key: Int,
        val value: Int
    )

    val list = ArrayList<MyEntry>(50_000)
    val map = HashMap<Int, Int>()
    for (i in 0 until 50_000) {
        list.add(MyEntry(i, i*2))
        map.put(i, i*2)
    }

    run {
        val t1 = System.currentTimeMillis()
        for (i in 0 until 100_000) {
            val key = Random.nextInt() % 50_000
            for (record in list) {
                if (record.key == key) {
                    val value = record.value
                    break
                }
            }
        }
        val t2 = System.currentTimeMillis()
        println(t2-t1)
    }

    run {
        val t1 = System.currentTimeMillis()
        for (i in 0 until 100_000) {
            val key = Random.nextInt() % 50_000
            val value = map.get(key)
        }
        val t2 = System.currentTimeMillis()
        println(t2-t1)
    }
}
