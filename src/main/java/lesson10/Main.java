package lesson10;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        race.begin();
    }
}