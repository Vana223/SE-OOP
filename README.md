# Завдання 1

### Написати просту консольну програму

![](/screen/Screenshot_1.png)

# Завдання 2

### Визначити 16-річне подання цілісних значень координат (x = v0 * cos (α) * t; y = v0 * sin (α) * t - (g * t * t) / 2) положення фізичного тіла при русі під дією сили тяжіння
````java
public class task {
    /**
     * Клас для 16-річних подання цілісних значень координат
     * @param args
     */
    public static void main(String[] args) {
        double v0 = 10;
        double alpha = Math.toRadians(45);
        double g = 9.8;
        double t;

        for (int i = 0; i < 16; i++) {
            t = i;
            double x = v0 * Math.cos(alpha) * t;
            double y = v0 * Math.sin(alpha) * t - (g * t * t) / 2;

            System.out.println("Час " + t + ": x = " + x + ", y = " + y);
        }
    }
}
````

### Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень
````java
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Клас для представлення результутату 16-річних цілісних значень координат 
 */

public class Result implements Serializable {
    private double v0;
    private double alpha;
    private double g;
    private double[][] coordinates;

    public Result(double v0, double alpha, double g, double[][] coordinates) {
        this.v0 = v0;
        this.alpha = alpha;
        this.g = g;
        this.coordinates = coordinates;
    }

    public double getV0() {
        return v0;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getG() {
        return g;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Double.compare(result.v0, v0) == 0 &&
                Double.compare(result.alpha, alpha) == 0 &&
                Double.compare(result.g, g) == 0 &&
                Arrays.deepEquals(result.coordinates, coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(v0, alpha, g);
        result = 31 * result + Arrays.deepHashCode(coordinates);
        return result;
    }
}

````

### Використовуючи агрегування, розробити клас для знаходження рішення задачі
```` java
/**
 * Клас для агрегування результатів 16-річних подання цілісних значень координат
 */

public class solver {
    
    private double v0;
    private double alpha; 
    private double g;

    public solver(double v0, double alpha, double g) {
        this.v0 = v0;
        this.alpha = alpha;
        this.g = g;
    }

    public Result solve(int timeSteps) {
        double[][] coordinates = new double[timeSteps][2];

        for (int i = 0; i < timeSteps; i++) {
            double t = i;
            double x = v0 * Math.cos(alpha) * t;
            double y = v0 * Math.sin(alpha) * t - (g * t * t) / 2;

            coordinates[i][0] = x;
            coordinates[i][1] = y;
        }

        return new Result(v0, alpha, g, coordinates);
    }
}
````

### Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів

````java
import java.io.*;

/**
 * Клас для демонстрації обчислення серіалізації/десеріалізації результатів 16-річного подання цілісних значень координат
 */

public class demo {
    public static void main(String[] args) {
        solver solver = new solver(10, Math.toRadians(45), 9.8);
        Result result = solver.solve(16);

        saveResult(result, "result.ser");

        Result restoredResult = restoreResult("result.ser");

        if (restoredResult != null) {
            System.out.println("Відновлений об'єкт:");
            printResult(restoredResult);
        }
    }

    private static void saveResult(Result result, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(result);
            System.out.println("Об'єкт збережено у файл " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Result restoreResult(String filename) {
        Result result = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            result = (Result) inputStream.readObject();
            System.out.println("Об'єкт відновлено з файлу " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void printResult(Result result) {
        System.out.println("Параметри об'єкта:");
        System.out.println("Початкова швидкість: " + result.getV0());
        System.out.println("Кут початкової швидкості: " + result.getAlpha());
        System.out.println("Прискорення вільного падіння: " + result.getG());
        System.out.println("Координати:");
        double[][] coordinates = result.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            System.out.println("Час " + i + ": x = " + coordinates[i][0] + ", y = " + coordinates[i][1]);
        }
    }
}
````

### Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації
````java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Клас для тестування правильності обчислення та серіалізації/десеріалізації результатів 16-річного подання цілісних значень координат
 */

public class Test {
    public static void main(String[] args) {
        solver solver = new solver(10, Math.toRadians(45), 9.8);
        Result expectedResult = solver.solve(16);

        testSerialization(expectedResult);
    }

/**
* Метод для тестування правильності обчислень.
*/

    private static void testSerialization(Result expectedResult) {

        String filename = "test_result.ser";
        saveResult(expectedResult, filename);

        Result restoredResult = restoreResult(filename);

        if (expectedResult.equals(restoredResult)) {
            System.out.println("Серіалізація/десеріалізація успішна.");
        } else {
            System.out.println("Помилка: Результати не збігаються.");
        }
    }

    private static void saveResult(Result result, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(result);
            System.out.println("Об'єкт збережено у файл " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Result restoreResult(String filename) {
        Result result = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            result = (Result) inputStream.readObject();
            System.out.println("Об'єкт відновлено з файлу " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}

````

### Результати:

![](/screen/Screenshot_2.png)

![](/screen/Test.png)