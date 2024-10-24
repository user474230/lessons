package lesson10;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    public static final int COMPETITORS_COUNT_IN_TUNNEL = 2;

    private Semaphore semaphore = new Semaphore(COMPETITORS_COUNT_IN_TUNNEL);

    @Override
    public void overcome(Car c) {
        try {
            overcoveInternal(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void overcoveInternal(Car c) throws InterruptedException {
        System.out.println(c.getName() + " готовится к этапу(ждет): " + description + "     " + System.currentTimeMillis());
        semaphore.acquire();
        System.out.println(c.getName() + " начал этап: " + description + "     " + System.currentTimeMillis());
        Thread.sleep(length / c.getSpeed() * 1000);
        System.out.println(c.getName() + " закончил этап: " + description + "     " + System.currentTimeMillis());
        semaphore.release();
    }
}