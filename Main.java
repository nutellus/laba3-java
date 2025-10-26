import java.util.*;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Работа с коробкой (Box<Integer>)");
            System.out.println("2. Демо интерфейса \"Сравнимое\"");
            System.out.println("3. Поиск максимума среди коробок");
            System.out.println("4. Функция (apply) — три примера с вводом");
            System.out.println("5. Фильтр (test) — три примера с вводом");
            System.out.println("0. Выход");

            int choice = readInt("Выберите действие: ");

            switch (choice) {
                case 1 -> boxMenu();
                case 2 -> comparableMenu();
                case 3 -> maxMenu();
                case 4 -> functionMenu();
                case 5 -> filterMenu();
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Нет такого пункта.");
            }
        }
    }

    // ---------------- Задание 1.1: Коробка ----------------
    private static void boxMenu() {
        Box<Integer> box = new Box<>();

        while (true) {
            System.out.println("\n--- МЕНЮ КОРОБКИ ---");
            System.out.println("1. Положить число");
            System.out.println("2. Извлечь число");
            System.out.println("3. Проверить пустоту");
            System.out.println("4. Показать содержимое");
            System.out.println("0. Назад");
            int choice = readInt("Ваш выбор: ");

            switch (choice) {
                case 1 -> {
                    if (!box.isEmpty()) {
                        System.out.println("Коробка уже занята.");
                        break;
                    }
                    int v = readInt("Введите целое число: ");
                    try {
                        box.put(v);
                        System.out.println("Число добавлено.");
                    } catch (IllegalStateException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
                case 2 -> {
                    if (box.isEmpty()) {
                        System.out.println("Коробка пустая.");
                    } else {
                        int extracted = box.get();
                        System.out.println("Извлечено: " + extracted);
                    }
                }
                case 3 -> System.out.println(box.isEmpty() ? "Коробка пустая." : "Коробка не пустая.");
                case 4 -> System.out.println(box);
                case 0 -> { return; }
                default -> System.out.println("Нет такого пункта.");
            }
        }
    }

    // ---------------- Задание 1.3: Сравнимое ----------------
    private static void comparableMenu() {
        IntHolder holder = new IntHolder(readInt("Введите начальное число для IntHolder: "));

        while (true) {
            System.out.println("\n--- СРАВНИМОЕ ---");
            System.out.println("1. Сравнить с числом");
            System.out.println("2. Задать новое базовое число");
            System.out.println("0. Назад");
            int choice = readInt("Ваш выбор: ");

            switch (choice) {
                case 1 -> {
                    int x = readInt("Введите число для сравнения: ");
                    int res = holder.sravnit(x);
                    if (res < 0)      System.out.println(holder + " < " + x);
                    else if (res > 0) System.out.println(holder + " > " + x);
                    else              System.out.println(holder + " == " + x);
                }
                case 2 -> holder = new IntHolder(readInt("Новое базовое число: "));
                case 0 -> { return; }
                default -> System.out.println("Нет такого пункта.");
            }
        }
    }

    // ---------------- Задание 2.2: Поиск максимума ----------------
    private static void maxMenu() {
        int n = readInt("Сколько коробок создать? ");
        @SuppressWarnings("unchecked")
        Box<? extends Number>[] boxes = new Box[n];

        for (int i = 0; i < n; i++) {
            double value = readDouble("Коробка #" + (i + 1) + ", число: ");
            Box<Number> b = new Box<>();
            b.put(value);
            boxes[i] = b;
        }

        double max = MaxFinder.findMax(boxes);
        System.out.println("Максимальное значение: " + max);
    }

    // ---------------- Задание 3.1: Функция (apply) ----------------
    private static void functionMenu() {
        while (true) {
            System.out.println("\n--- ФУНКЦИЯ (apply) ---");
            System.out.println("1. Строки → длины (ввод с клавиатуры)");
            System.out.println("2. Числа → модуль (ввод с клавиатуры)");
            System.out.println("3. Список массивов int[] → список максимумов");
            System.out.println("0. Назад");
            int choice = readInt("Ваш выбор: ");

            switch (choice) {
                case 1 -> {
                    List<String> data = readStringList();
                    List<Integer> res = Functions.applyToEach(data, s -> s.length());
                    System.out.println("Длины строк: " + res);
                }
                case 2 -> {
                    List<Integer> data = readIntList();
                    List<Integer> res = Functions.applyToEach(data, Math::abs);
                    System.out.println("Модули чисел: " + res);
                }
                case 3 -> {
                    List<int[]> data = readListOfIntArrays();
                    List<Integer> res = Functions.applyToEach(
                            data,
                            arr -> {
                                if (arr.length == 0) return Integer.MIN_VALUE;
                                int max = arr[0];
                                for (int i = 1; i < arr.length; i++) if (arr[i] > max) max = arr[i];
                                return max;
                            }
                    );
                    System.out.print("Исходные массивы: ");
                    System.out.println(formatIntArrays(data));
                    System.out.println("Максимумы: " + res);
                }
                case 0 -> { return; }
                default -> System.out.println("Нет такого пункта.");
            }
        }
    }

    // ---------- Задание 3.2 "Фильтр" ----------
    private static void filterMenu() {
        while (true) {
            System.out.println("\n--- ФИЛЬТР (test) ---");
            System.out.println("1. Строки: оставить длиной ≥ 3");
            System.out.println("2. Числа: оставить только положительные");
            System.out.println("3. Список массивов int[]: оставить только те, где НЕТ положительных");
            System.out.println("0. Назад");
            int choice = readInt("Ваш выбор: ");

            switch (choice) {
                case 1 -> {
                    java.util.List<String> data = readStringList();
                    java.util.List<String> res = Filters.filter(
                            data,
                            s -> s != null && s.length() >= 3
                    );
                    System.out.println("После фильтра: " + res);
                }
                case 2 -> {
                    java.util.List<Integer> data = readIntList();
                    java.util.List<Integer> res = Filters.filter(
                            data,
                            x -> x != null && x > 0
                    );
                    System.out.println("После фильтра: " + res);
                }
                case 3 -> {
                    java.util.List<int[]> data = readListOfIntArrays();
                    java.util.List<int[]> res = Filters.filter(
                            data,
                            arr -> {
                                if (arr == null) return false;
                                for (int v : arr) if (v > 0) return false;
                                return true;
                            }
                    );
                    System.out.print("Исходные массивы: ");
                    System.out.println(formatIntArrays(data));
                    System.out.print("После фильтра (оставлены массивы без положительных): ");
                    System.out.println(formatIntArrays(res));
                }
                case 0 -> { return; }
                default -> System.out.println("Нет такого пункта.");
            }
        }
    }



    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private static List<String> readStringList() {
        int n = readInt("Сколько строк ввести? ");
        List<String> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            System.out.print("Строка #" + (i + 1) + ": ");
            String s = SC.nextLine();
            list.add(s);
        }
        return list;
    }

    private static List<Integer> readIntList() {
        int n = readInt("Сколько чисел ввести? ");
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(readInt("Число #" + (i + 1) + ": "));
        }
        return list;
    }

    private static List<int[]> readListOfIntArrays() {
        int m = readInt("Сколько массивов? ");
        List<int[]> data = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            System.out.println("Массив #" + (i + 1));
            int len = readInt("  Длина: ");
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) arr[j] = readInt("  arr[" + j + "] = ");
            data.add(arr);
        }
        return data;
    }

    private static String formatIntArrays(List<int[]> data) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < data.size(); i++) {
            sb.append(Arrays.toString(data.get(i)));
            if (i + 1 < data.size()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
