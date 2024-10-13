package lesson5;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Box<Apple> box1 = new Box<Apple>()
                .addItem(new Apple())
                .addItem(new Apple())
                .addItem(new Apple());
        Box<Apple> box2 = new Box<Apple>()
                .addItem(new Apple())
                .addItem(new Apple())
                .addItem(new Apple());
        Box<Orange> box3 = new Box<Orange>()
                .addItem(new Orange())
                .addItem(new Orange());
        Box<Orange> box4 = new Box<Orange>()
                .addItem(new Orange())
                .addItem(new Orange());

        System.out.println(box3.compare(box1));
        System.out.println(box3.compare(box4));

        box1.move(box2);
        box3.move(box4);
        System.out.println(box2.compare(box4));
        System.out.println(box1.compare(box3));
        System.out.println(box1.compare(box4));
    }

    public static <T> void case1(T[] array, int ind1, int ind2) {
        T tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    public static <T> ArrayList<T> case2(T[] array) {
        ArrayList res = new ArrayList();
        for (T item : array) {
            res.add(item);
        }
        return res;
    }
}
