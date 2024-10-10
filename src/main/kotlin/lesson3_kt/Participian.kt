package lesson3_kt

interface Participian {
    fun run(distance : Int) : Boolean
    fun jump(height: Int) : Boolean
    fun performAction(value: Int, maxValue: Int, action: String, actor : String) : Boolean {
        if (maxValue >= value) {
            println("$actor $action")
            return true
        }
        println("$actor не $action")
        return false
    }
}