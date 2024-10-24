package lesson10;

abstract class Stage {
    int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void overcome(Car c);
}
