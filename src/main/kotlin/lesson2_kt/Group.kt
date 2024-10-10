package lesson2_kt

//1.Создайте класс Сотрудник, с полями: имя, email, возраст, должность. Сотрудник должен уметь отпечатать в консоль информацию о себе;
//2.Создайте класс Группа, включающий в себя название группы и массив из максимум 10 сотрудников (не все элементы массива могут быть заполнены).
// Реализуйте возможность добавлять сотрудников в этот массив, удалять их из него по индексу, и удалять всех разом;
//3.В классе Группа должен быть метод, позволяющий отпечатать информацию обо всех сотрудниках, входящих в эту группу;

data class Group (
    var name : String
) {
    private var index = -1
    private var array = arrayOfNulls<Employee>(10)

    fun add(employee: Employee) {
        if (index == array.size-1) {
            throw RuntimeException("В массиве нет места")
        }
        index++
        array[index] = employee
    }

    fun clear() {
        index = -1
        array = arrayOfNulls<Employee>(10)
    }

    fun remove(ind : Int) {
        if (ind > index) {
            return
        }
        array[ind] = null
        for (i in ind..index) {
            if (i+1 == array.size)
                break
            array[i] = array[i+1]
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