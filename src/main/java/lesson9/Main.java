package lesson9;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) {
        case1();
        case2();
        case3();
    }

    static final int COUNT = 100_000_000;
    static int[] array = new int[COUNT];

    static {
        Random r = new Random();
        for (int i = 0; i < COUNT; ++i) {
            array[i] = r.nextInt() % 100_000;
        }
    }

    public static void case1() {
        long t1 = System.currentTimeMillis();
        int max = Arrays.stream(array).max().orElse(-1);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    public static void case2() {
        long t1 = System.currentTimeMillis();
        int max = Arrays.stream(array).parallel().max().orElse(-1);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    static class MyRecursiveTask extends RecursiveTask<Integer> {
        int min;
        int max;

        public MyRecursiveTask(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        protected Integer compute() {
            int result = 0;
            if (max - min > 10_000_000) {
                int mid = (min + max) / 2;
                MyRecursiveTask task1 = new MyRecursiveTask(min, mid);
                MyRecursiveTask task2 = new MyRecursiveTask(mid + 1, max);

                task1.fork();
                task2.fork();

                result = task1.join();
                result = Math.max(result, task2.join());
            } else {
                result = array[min];
                for (int i = min; i <= max; ++i) {
                    if (result < array[i]) {
                        result = array[i];
                    }
                }
            }
            return result;
        }
    }

    public static void case3() {
        long t1 = System.currentTimeMillis();
        int result = ForkJoinPool.commonPool().invoke(new MyRecursiveTask(0, COUNT - 1));
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

}
