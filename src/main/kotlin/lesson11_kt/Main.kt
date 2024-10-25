package lesson11_kt

@Test
class Test1 {
    @BeforeSuite
    fun before() = println("before")

    @AfterSuite
    fun after() = println("after")

    @Test
    fun case1() = println("0")

    @Test(priority = 1)
    fun case2() = println("1")

    @Test(priority = 2)
    fun case3() = println("2")

    @Test(priority = 5)
    fun case4() = println("5")

    @Test(priority = 1)
    fun case5() = println("1")
}

fun main() {
    start(Test1())
}
