package lesson2_kt

data class Employee(
    var name: String,
    var email: String,
    var age: Int,
    var post: Post
) {
    override fun toString(): String {
        return "Employee(name='$name', email='$email', age=$age, post=${post.fullName})"
    }
}