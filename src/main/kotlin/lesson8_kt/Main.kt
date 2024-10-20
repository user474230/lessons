package lesson8_kt


fun main() {
    case5()
}

fun case1() {
    val str = """
        Дороги в Эквадоре практически идеальные хотя населенные пункты выглядят очень бедно 
        На дорогах много интересных машин например очень много грузовиков древних Фордов которые я никогда раньше 
        не видел А еще несколько раз на глаза попадались старенькие Жигули А еще если кого-то обгоняешь и
        есть встречная машина она обязательно включает фары На больших машинах грузовиках и автобусах
        обязательно красуется местный тюнинг машины разукрашенные либо в наклейках и обязательно везде
        огромное множество светодиодов как будто новогодние елки едут и переливаются всеми цветами"
    """.trimIndent().replace("\n", "")

    val result = str.split(" ")
        .filter { s -> s.length > 5 }
        .joinToString(" ")

    println(result)
}

fun case2() {
    val array = arrayOf(
        arrayOf("qwer", "wer","qwer", "qwer", "asd"),
        arrayOf("qwer", "wer","qwer", "qwer", "asd"),
        arrayOf("qwer2", "wer","qwer", "qwer", "asd"),
        arrayOf("qwer", "wer","qwer", "qwer3", "asd"),
        arrayOf("qwer", "wer1","qwer", "qwer", "asd")
    )

    val result = array.flatten().toSet()
    println(result)
}

fun case3() {
    val result = (100..200).filter { i -> i % 2 == 0 }.sum()
    println(result)
}

fun case4() {
    val array = arrayOf("qwer","sadf","qwefdfe")
    val result = array.map { s -> s.length }.sum()
    println(result)
}

fun case5() {
    val array = arrayOf("qwer","sadf","qwefdfe","qwerr","asdf","bdsf","fasf")
    val result = array.asSequence().sorted().take(3).toList()
    println(result)
}