package lesson11_kt

import java.lang.reflect.Method

fun start(obj: Any) {
    val testClass = obj.javaClass
    if (!testClass.isAnnotationPresent(Test::class.java))
        throw RuntimeException("Нет аннотации @Test")
    val tests = mutableListOf<Method>()
    val before = mutableListOf<Method>()
    val after = mutableListOf<Method>()

    testClass.declaredMethods.forEach { method ->
        if (method.isAnnotationPresent(Test::class.java))
            tests.add(method)
        if (method.isAnnotationPresent(BeforeSuite::class.java))
            before.add(method)
        if (method.isAnnotationPresent(AfterSuite::class.java))
            after.add(method)
    }

    if (before.size > 1)
        throw RuntimeException("Более одного метода @Before")

    if (after.size > 1)
        throw RuntimeException("Более одного метода @After")

    if (before.isNotEmpty())
        if (before[0].isAnnotationPresent(Test::class.java))
            throw RuntimeException("У метода @Before аннотация @Test")

    if (after.isNotEmpty())
        if (after[0].isAnnotationPresent(Test::class.java))
            throw RuntimeException("У метода @After аннотация @Test")

    tests.sortBy { method: Method -> method.getAnnotation(Test::class.java).priority }

    before.forEach { it.invoke(obj) }
    tests.forEach { it.invoke(obj) }
    after.forEach { it.invoke(obj) }
}