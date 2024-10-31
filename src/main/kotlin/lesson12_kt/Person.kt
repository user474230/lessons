package lesson12_kt

@DbTable("person")
data class Person (
    @DbId
    @DbColumn
    @JvmField var id : Long = 0,
    @DbColumn
    @JvmField var fio: String = "",
    @DbColumn
    @JvmField var phone: String = "",
    @DbColumn
    @JvmField var age: Int = 0,
    @JvmField var cache: String = "",
)