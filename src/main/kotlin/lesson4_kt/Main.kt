package lesson4_kt

//1Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
// При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//2Далее метод должен пройтись по всем элементам массива, преобразовать в int и
// просуммировать. Если в каком-то элементе массива преобразование не удалось
// (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException
// с детализацией, в какой именно ячейке лежат неверные данные.
//3В методе main() вызвать полученный метод, обработать возможные
// исключения MyArraySizeException и MyArrayDataException и вывести результат расчета.
fun main() {
    try {
        val res = myFunction(arrayOf(
            arrayOf("1","1","1","1"),
            arrayOf("1","1","1","1"),
            arrayOf("1","1","1","1"),
            arrayOf("1","1","1","1")
        ))
        println(res)
    } catch (e : MyArraySizeException) {
        println("Ошибка в размере массива")
    } catch (e : MyArrayDataException) {
        println("Не удалось выполнить. Ошибка: ${e.message}")
    }

    runCatching {
        val res = myFunction(arrayOf(
            arrayOf("1","1","1","1"),
            arrayOf("1","1","1","1"),
            arrayOf("1","1","1","1"),
            arrayOf("1","1","1","1")
        ))
        println(res)
    }.onFailure { exception ->
        println("Выброшено исключение $exception")
    }
}

fun myFunction(array : Array<Array<String>>) : Int{
    if (array.size != 4) {
        throw MyArraySizeException()
    }
    for (row in array) {
        if (row.size !=4) {
            throw MyArraySizeException()
        }
    }

    var sum = 0
    array.forEachIndexed { i, row ->
        row.forEachIndexed{j, item ->
            val curr = item.toIntOrNull()
            if (curr == null) {
                throw MyArrayDataException("Ошибка в элементе $i $j")
            }
            sum += curr
        }
    }

    return sum
}