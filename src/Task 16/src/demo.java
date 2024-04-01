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
