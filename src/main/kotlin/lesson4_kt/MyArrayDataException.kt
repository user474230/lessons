package lesson4_kt

class MyArrayDataException : RuntimeException {
    constructor() : super()
    constructor(msg : String) : super(msg)
}