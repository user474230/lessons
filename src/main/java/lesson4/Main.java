package lesson4;

public class Main {
    public static void main(String[] args) {
        try {
            String[][] array = {
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"},
                    {"1", "1", "1", "1"}
            };

            int result = myFunction(array);
            System.out.println(result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.toString());
        }
    }

    public static int myFunction(String[][] array) {
        if (array.length != 4) {
            throw new MyArraySizeException();
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException();
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                try {
                    int val = Integer.parseInt(array[i][j]);
                    sum += val;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Ошибка в %d %d", i, j));
                }
            }
        }
        return sum;
    }
}
