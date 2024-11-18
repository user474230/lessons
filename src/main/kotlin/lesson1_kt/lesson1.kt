package org.example.lesson1

import java.lang.management.MemoryUsage
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {
//    val letters = 'A'..'z' union 'А'..'я' union setOf('ё', 'Ё')
//    val shielded = setOf('\\', '\"')
    var str = "{\"Краснодар \r\nSTD Поступление \r\n私わたしワタシ\t\tзаработной платы/иных выплат Salary по реестру №Z_0000115902_20240918_200079 от 2024-09-18. Без НДС.\",\n" +
            "  \"PAYMENTCONDITION\" : \"1\",\n" +
            "  \"CURRENCYCODE\" : \"643\"\n" +
            "}"
    println(str)
//
//    val builder = StringBuilder()
//    var lastCharIsSpace = false
//    str.forEach { char ->
//        when {
//            Character.isLetter(char) -> if (char in letters) {
//                    builder.append(char)
//                    lastCharIsSpace = false
//            }
            Character.isWhitespace(char) -> if (!lastCharIsSpace) {
//                    builder.append(' ')
//                    lastCharIsSpace = true
//            }
//            char == '\"' || char == '\\' -> {
//                builder.append('\\').append(char)
//                lastCharIsSpace = false
//            }
//            else -> {
//                builder.append(char)
//                lastCharIsSpace = false
//            }
//        }
//    }
//    println(builder.toString())

  //  ar complianceContainerRaw = executorService.envelopeCreate(it)
//    str = str.replace("[\\r\\n\\t\\f\\v]".toRegex(), "")
        //.replace("[\"\\/]",){ matchResult : MatchResult -> matchResult.value }
  //  complianceContainer = "\\\"PRIMARY_DETAILS\\\""
    println(str)

//    val value = StringBuilder(str)
//        .filter { char ->
//            when {
//                Character.isLetter(char) -> char in letters
//                Character.isWhitespace(char) -> char == ' '
//                else -> true
//            }
//        }.replace(Regex("\\s+"), " ")
//        .map { char -> when {
//                char in shielded -> "\\$char"
//                else -> char
//            }
//        }.joinToString("")
//    println(value)
//    println(value.replace(Regex("\\s+"), " "))
}

//+ 1.Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах
// от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
fun case1(x : Int, y : Int) = (10..20).contains(x+y)

//+ 2.Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
// положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
fun case2(x: Int) {
    if (x>=0) println("Положительное")
    else println("Отрицательное")
}

//+ 3.Написать метод, которому в качестве параметра передается целое число.
// Метод должен вернуть true, если число отрицательное.
fun case3(x: Int) = x < 0

//+ 4.Написать метод, которому в качестве параметра передается строка, обозначающая имя.
// Метод должен вывести в консоль сообщение «Привет, указанное_имя!».
fun case4(name : String) = println("Привет, $name!")

//+ 5.Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
// С помощью цикла и условия заменить 0 на 1, 1 на 0;
fun case5() {
    var array = arrayOf(1, 1, 0, 0, 1, 0, 1, 1, 0, 0)
    for (i in 0 until array.size) {
        array[i] =
            if (array[i] == 0) 1
            else 0
    }
    println(array.joinToString(", "))
}

//+ 6.Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 2 5 8 11 14 17 20 23;
fun case6() {
    val array = Array<Int>(8){0}
    for (i in 0 until array.size) {
        array[i] = i*3 + 2;
    }
    println(array.joinToString(", "))
}

//+ 7.Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом,
// и числа меньшие 6 умножить на 2;
fun case7() {
    val array = arrayOf(1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1)
    for (i in 0 until array.size) {
        if (array[i] < 6) {
            array[i] *=2;
        }
    }
    println(array.joinToString(", "))
}

//+ 8.Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
// и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
fun case8() {
    val size = 5
    var array = Array<Array<Int>>(size){
        Array<Int>(size){0}
    }
    for (i in 0 until array.size) {
        array[i][i] = 1
        array[size - i - 1][i] = 1
    }
    println(Arrays.deepToString(array))
}

//+ 9.* Задать одномерный массив и найти в нем минимальный и максимальный элементы;
fun case9() {
    val array = arrayOf(1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1)
    var min = array[0]
    var max = array[0]
    for (i in 1 until array.size) {
        min = min(min, array[i])
        max = max(max, array[i])
    }
    println("min: $min   max: $max")
}

//+10.* Написать метод, который определяет, является ли год високосным, и выводит сообщение
// в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
fun case10(year: Int) {
    var result = false
    if (year%4 == 0) result = true
    if (year%100 == 0 && year % 400 != 0) result = false
    if (result) println("Високосный")
    else println("Не високосный")
}

//+ * Написать метод, в который передается не пустой одномерный целочисленный массив,
// метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
// граница показана символами ||, эти символы в массив не входят.
fun case11(array: Array<Int>) : Boolean {
    var leftInd = -1
    var rightInd = array.size
    var res = 0
    while (true) {
        if (res < 0) {
            leftInd++;
            if (leftInd==rightInd)
                break
            res += array[leftInd]
        } else {
            rightInd--;
            if (leftInd==rightInd)
                break
            res -= array[rightInd]
        }
    }
    return res == 0
}
