package lesson8;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        case5();
    }

    public static void case1() {
        String str = "Дороги в Эквадоре практически идеальные хотя населенные пункты выглядят очень бедно " +
                "На дорогах много интересных машин например очень много грузовиков  древних Фордов которые я " +
                "никогда раньше не видел А еще несколько раз на глаза попадались старенькие Жигули А еще если " +
                "кого-то обгоняешь и есть встречная машина она обязательно включает фары На больших машинах " +
                "грузовиках и автобусах обязательно красуется местный тюнинг машины разукрашенные либо в наклейках " +
                "и обязательно везде огромное множество светодиодов как будто новогодние елки едут и переливаются " +
                "всеми цветами";

        String res = Arrays.stream(str.split(" "))
                .map(String::toLowerCase)
                .filter(s -> s.length() > 5)
                .collect(Collectors.joining(" "));

        System.out.println(res);
    }

    public static void case2() {
        String[][] array = {
                {"qwer", "qwerty", "qwer", "qss", "qrr"},
                {"qwer", "qwerty2", "qwer", "qss3", "qrr"},
                {"qwer", "qwerty", "qwer", "qss", "qrr"},
                {"qwer", "qwerty2", "qwer", "qss3", "qrr"},
                {"qwer", "qwqwerty", "qwer", "qss", "qrr"}};

        Set<String> result = Arrays.stream(array)
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());

        System.out.println(result);
    }

    public static void case3() {
        int result = IntStream.rangeClosed(100, 200)
                .filter(i -> i % 2 == 0)
                .sum();

        System.out.println(result);
    }

    public static void case4() {
        String[] array = {"qwer", "qwerty", "asdf", "asdfzxc"};
        int result = Arrays.stream(array)
                .mapToInt(String::length)
                .sum();

        System.out.println(result);
    }

    public static void case5() {
        String[] array = {"qwer", "qwerty", "asdf", "asdfzxc", "bdddasf", "dwqer"};
        List<String> list = Arrays.stream(array)
                .sorted()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(list);
    }
}
