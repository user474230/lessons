package lesson11_kt

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class Test(val priority : Int = 0)
