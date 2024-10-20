package lesson6;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        case2();
    }

    public static void case1() {
        String[] words = new String[] {"qwe", "qwer", "qwerty", "qWe", "Qwe", "QWE", "QWER", "Qwerty", "a", "asdv", "A"};
        Map<String, Integer> result = new HashMap<>();
        for (String s : words) {
                String key = s.toLowerCase();
                Integer count = result.getOrDefault(key, 0);
                result.put(key, count + 1);
        }
        System.out.println(result);
    }

    public static void case2() {
        Dictionary d = new Dictionary();
        d.add("12345","qwer");
        d.add("12346","qwert");
        d.add("12347","qwerty");
        d.add("12348","qwert");
        d.add("12349","qwer");
        d.add("12349","qwer");
        d.add("12349","qwer");
        d.add("12349","qwer");
        d.add("12349","qwer");
        d.add("12349","qwer");

        System.out.println(d.get("qwer"));
        System.out.println(d.get("qwert"));
        System.out.println(d.get("qwerty"));
        System.out.println(d.get("asd"));
    }
}