package lesson12_kt

import java.sql.DriverManager

fun main(args: Array<String>) {
    Class.forName("org.postgresql.Driver")

    try {
        DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
            .use { connection ->
                val repository = ReflectionRepository(Person::class.java, connection)
                val p = Person(fio = "qwer", phone = "1234", age = 20, cache = "tempData");
                val p1 = repository.addOne(p)
                println(p1)
                //repository.deleteOne(47L)
                //repository.deleteAll()
                val p2 = repository.getOne(71L)
                println(p2)
                println(repository.getAll())
            }

    } catch (e : Exception) {
        e.printStackTrace()
    }
}