package lesson7;

import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        case3();
    }


//300_000 обращений
//                    10     100     10000    100000
//ArrayList:           1       1         1         1
//LinkedList:          1       1      1557     15213
    public static void case1() {
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();
        ArrayList<Integer> al4 = new ArrayList<>();
        ArrayList<Integer> al5 = new ArrayList<>();

        LinkedList<Integer> ll1 = new LinkedList<>();
        LinkedList<Integer> ll2 = new LinkedList<>();
        LinkedList<Integer> ll4 = new LinkedList<>();
        LinkedList<Integer> ll5 = new LinkedList<>();

        for (int i=0; i< 10; ++i) {
            al1.add(i);
            ll1.add(i);
        }

        for (int i=0; i< 100; ++i) {
            al2.add(i);
            ll2.add(i);
        }

        for (int i=0; i< 10_000; ++i) {
            al4.add(i);
            ll4.add(i);
        }

        for (int i=0; i< 100_000; ++i) {
            al5.add(i);
            ll5.add(i);
        }

        Function<List<Integer>,Long> tester = integers -> {
            long t1 = System.currentTimeMillis();
            for (int i=0; i < 300_000; ++i) {
                Integer val = integers.get(integers.size()/2);
                val += 10;
            }
            long t2 = System.currentTimeMillis();
            return t2 - t1;
        };

        long al1r = tester.apply(al1);
        long al2r = tester.apply(al2);
        long al4r = tester.apply(al4);
        long al5r = tester.apply(al5);

        long ll1r = tester.apply(ll1);
        long ll2r = tester.apply(ll2);
        long ll4r = tester.apply(ll4);
        long ll5r = tester.apply(ll5);

        System.out.println(al1r);
        System.out.println(al2r);
        System.out.println(al4r);
        System.out.println(al5r);

        System.out.println(ll1r);
        System.out.println(ll2r);
        System.out.println(ll4r);
        System.out.println(ll5r);
    }

//                    100      10000    100000
//ArrayList:            1          2        70
//LinkedList:           1         22      1957
    public static void case2() {
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();
        ArrayList<Integer> al3 = new ArrayList<>();

        LinkedList<Integer> ll1 = new LinkedList<>();
        LinkedList<Integer> ll2 = new LinkedList<>();
        LinkedList<Integer> ll3 = new LinkedList<>();

        for (int i=0; i< 100; ++i) {
            al1.add(i);
            ll1.add(i);
        }

        for (int i=0; i< 10_000; ++i) {
            al2.add(i);
            ll2.add(i);
        }

        for (int i=0; i< 100_000; ++i) {
            al3.add(i);
            ll3.add(i);
        }

        Function<List<Integer>,Long> tester = integers -> {
            long t1 = System.currentTimeMillis();
            int count = integers.size()/2;
            for (int i=0; i < count; ++i) {
                integers.remove(integers.size()/2);
            }
            long t2 = System.currentTimeMillis();
            return t2 - t1;
        };

        long al1r = tester.apply(al1);
        long al2r = tester.apply(al2);
        long al3r = tester.apply(al3);

        long ll1r = tester.apply(ll1);
        long ll2r = tester.apply(ll2);
        long ll3r = tester.apply(ll3);

        System.out.println(al1r);
        System.out.println(al2r);
        System.out.println(al3r);

        System.out.println(ll1r);
        System.out.println(ll2r);
        System.out.println(ll3r);
    }

//ArrayList<MyEntry>:           6500
//HashMap<Integer, Integer>:    5
    public static void case3() {
        class MyEntry {
            public MyEntry(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }

            final Integer key;
            final Integer value;
        }

        ArrayList<MyEntry> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i < 50_000; ++i) {
            list.add(new MyEntry(i, i));
            map.put(i,i);
        }

        Random rnd = new Random();

        {
            long t1 = System.currentTimeMillis();
            for (int i=0; i < 100_000; ++i) {
                int key = rnd.nextInt()%50_000;
                for (MyEntry entry : list) {
                    if (entry.key == key) {
                        Integer res = entry.value;
                        break;
                    }
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println(t2-t1);
        }

        {
            long t1 = System.currentTimeMillis();
            for (int i=0; i < 100_000; ++i) {
                int key = rnd.nextInt()%50_000;
                Integer res = map.get(key);
            }
            long t2 = System.currentTimeMillis();
            System.out.println(t2-t1);
        }
    }
}
