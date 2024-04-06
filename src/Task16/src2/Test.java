package src.Task16.src2;
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
