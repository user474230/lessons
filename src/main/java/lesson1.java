import java.util.Arrays;

public class lesson1 {
    public static void main(String[] args) {
        case10(280);
    }

    //+ 1.Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах
    // от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
    public static boolean case1(int x, int y) {
        final int sum = x + y;
        return sum >= 10 && sum <= 20;
    }

    //+ 2.Написать метод, которому в качестве параметра передается целое число, метод должен
    // напечатать в консоль, положительное ли число передали или отрицательное.
    // Замечание: ноль считаем положительным числом.
    public static void case2(int x) {
        String result = x >= 0 ? "positive" : "negative";
        System.out.println(result);
    }

    //+ 3.Написать метод, которому в качестве параметра передается целое число.
    // Метод должен вернуть true, если число отрицательное.
    public static boolean case3(int x) {
        return x < 0;
    }

    //+ 4.Написать метод, которому в качестве параметра передается строка,
    // обозначающая имя. Метод должен вывести в консоль сообщение «Привет, указанное_имя!».
    public static void case4(String name) {
        System.out.printf("Привет, %s!", name);
    }

    //+ 5.Задать целочисленный массив, состоящий из элементов 0 и 1.
    // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void case5() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; ++i) {
            array[i] = (array[i] + 1) % 2;
        }
        System.out.println(Arrays.toString(array));
    }

    //+ 6.Задать пустой целочисленный массив размером 8.
    // С помощью цикла заполнить его значениями 2 5 8 11 14 17 20 23;
    public static void case6() {
        int[] array = new int[8];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i * 3 + 2;
        }
        System.out.println(Arrays.toString(array));
    }

    //+ 7.Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1  ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void case7() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //+ 8.Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void case8() {
        final int size = 5;
        int[][] array = new int[size][];
        for (int i = 0; i < size; ++i) {
            array[i] = new int[size];
        }

        for (int i = 0; i < size; ++i) {
            array[i][i] = 1;
            array[size - 1 - i][i] = 1;
        }

        System.out.println(Arrays.deepToString(array));
    }

    //9.* Задать одномерный массив и найти в нем минимальный и максимальный элементы;
    public static void case9() {
        int[] array = {1, 6, 4, 3, 8, 3, 9, 3};
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(array[i], max);
            min = Math.min(array[i], min);
        }

        System.out.printf("min: %d   max: %d", min, max);
    }

    //10.* Написать метод, который определяет, является ли год високосным, и выводит
    // сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го,
    // при этом каждый 400-й – високосный.
    public static void case10(int year) {
        boolean result = false;
        if (year % 4 == 0) {
            result = true;
        }
        if (year % 100 == 0 && year % 400 != 0) {
            result = false;
        }
        System.out.println(result ? "Високосный" : "Не високосный");
    }
}
