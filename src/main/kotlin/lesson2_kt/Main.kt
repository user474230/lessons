package lesson2_kt

fun main() {
    val group = Group("Nevosoft")
    group.add(Employee("Иван", "Ivan@ya.ru", 20, Post.QA))
    group.add(Employee("Иван1", "Ivan@ya.ru", 20, Post.QA))
    group.add(Employee("Иван2", "Ivan@ya.ru", 20, Post.QA))
    group.add(Employee("Иван3", "Ivan@ya.ru", 20, Post.QA))

    group.remove(2)
    group.add(Employee("Иван4", "Ivan@ya.ru", 20, Post.QA))
    group.add(Employee("Иван5", "Ivan@ya.ru", 20, Post.QA))
    println(group.toString())
}