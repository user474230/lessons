package lesson10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    public static final int COMPETITORS_COUNT = 4;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(COMPETITORS_COUNT);
    private CountDownLatch countDownLatch = new CountDownLatch(COMPETITORS_COUNT);

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    private List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatch.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        Car car = Arrays.stream(cars).min((o1, o2) -> (int) (o1.getFinishTime() - o2.getFinishTime())).get();
        System.out.println(car.getName() + " - победитель");
    }
}