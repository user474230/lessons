package lesson4;

public class MyArraySizeException extends RuntimeException {
    public MyArraySizeException() {
        super();
    }

    public MyArraySizeException(String msg) {
        super(msg);
    }
}
