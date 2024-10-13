package lesson5;

import java.util.ArrayList;

class Box<T extends Fruit> {
    ArrayList<T> list = new ArrayList<>();

    float getWeight() {
        final float[] sum = {0.f};
        list.forEach(t -> sum[0] += t.getSingleWeight());
        return sum[0];
    }

    public Box<T> addItem(T item) {
        list.add(item);
        return this;
    }

    public <T1 extends Fruit> boolean compare(Box<T1> box) {
        return box.getWeight() == getWeight();
    }

    public void move(Box<T> box) {
        list.addAll(box.list);
        box.list.clear();
    }
}
