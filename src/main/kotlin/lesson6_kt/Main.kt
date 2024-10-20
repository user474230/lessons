package lesson6_kt

import java.util.*

fun main() {
    case2()
}

fun case1() {
    val words = arrayOf("qwe","qweR", "qwer", "QWE", "X", "asd", "ASD", "asD")
    val result = mutableMapOf<String, Int>()

    words.forEach {
        val key = it.lowercase(Locale.getDefault())
        val count = result.getOrPut(key) { 0 }
        result[key] = count + 1
    }

    println(result)
}

fun case2() {
    val dictionary = Dictionary()
    dictionary.add("12345", "qwer")
    dictionary.add("12346", "qwert")
    dictionary.add("12347", "qwerty")
    dictionary.add("12348", "qwert")
    dictionary.add("12349", "qwer")
    dictionary.add("12349", "qwer")
    dictionary.add("12349", "qwer")
    dictionary.add("12349", "qwer")
    dictionary.add("12349", "qwer")
    dictionary.add("12349", "qwer")

    println(dictionary.get("qwer"))
    println(dictionary.get("qwert"))
    println(dictionary.get("qwerty"))
    println(dictionary.get("asd"))

}