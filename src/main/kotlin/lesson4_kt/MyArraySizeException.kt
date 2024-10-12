package lesson4_kt

class MyArraySizeException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor() : super()
}