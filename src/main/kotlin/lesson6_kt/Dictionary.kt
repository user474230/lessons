package lesson6_kt

class Dictionary {
    private val map = HashMap<String, HashSet<String>>()

    fun add(phone : String, name : String) {
        val record = map.getOrPut(name) { HashSet<String>() }
        record.add(phone)
    }

    fun get(name : String) : Set<String>? {
        return map[name]
    }
}