package lesson10;

public class Car implements Runnable {
    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;
    private Long finishTime = null;

    public Long getFinishTime() {
        return finishTime;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            runInternal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runInternal() throws Exception {
        System.out.println(this.name + " готовится     " + System.currentTimeMillis());
        Thread.sleep(500 + (int) (Math.random() * 800));
        System.out.println(this.name + " готов     " + System.currentTimeMillis());

        race.getCyclicBarrier().await();

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).overcome(this);
        }
        race.getCountDownLatch().countDown();
        finishTime = System.currentTimeMillis();
    }
}
