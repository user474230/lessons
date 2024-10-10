package lesson2_kt

data class Group(
    var name: String
) {
    private var index = -1
    private var array = arrayOfNulls<Employee>(10)

    fun add(employee: Employee) {
        if (index == array.size - 1) {
            throw RuntimeException("В массиве нет места")
        }
        index++
        array[index] = employee
    }

    fun clear() {
        index = -1
        array = arrayOfNulls<Employee>(10)
    }

    fun remove(ind: Int) {
        if (ind > index) {
            return
        }
        array[ind] = null
        for (i in ind..index) {
            if (i + 1 == array.size)
                break
            array[i] = array[i + 1]
        }
        --index
    }

    override fun toString(): String {
        var sb = StringBuilder()
        for (item in array) {
            if (item != null) {
                sb.append(item.toString() + "\n")
            }
        }
        return "Group(name='$name', array=$sb)"
    }
}