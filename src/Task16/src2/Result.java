package src.Task16.src2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для представлення результутату 16-річних цілісних значень координат 
 */

public class Result implements Serializable {
    private static List<Result> results = new ArrayList<>();
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

    public Result() {
        results = new ArrayList<>();
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }

    public void saveResults(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(results);
            System.out.println("Результати збережено у файл " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для десеріалізації
    @SuppressWarnings("unchecked")
    public void loadResults(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            results = (List<Result>) inputStream.readObject();
            System.out.println("Результати відновлено з файлу " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String formatResultText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Initial velocity: ").append(v0).append("\n");
        sb.append("Launch angle: ").append(alpha).append("\n");
        sb.append("Gravity: ").append(g).append("\n");
        sb.append("Coordinates:\n");
        for (int i = 0; i < coordinates.length; i++) {
            sb.append("Time ").append(i).append(": x = ").append(coordinates[i][0]).append(", y = ").append(coordinates[i][1]).append("\n");
        }
        return sb.toString();
    }

    public void printFormattedResult() {
        System.out.println(formatResultText());
    }

    public void saveResult() {
        results.add(this);
        System.out.println("Result saved.");
    }

    public void deleteResult() {
        results.remove(this);
        System.out.println("Result deleted.");
    }

    public static Result getResult(int index) {
        if (index >= 0 && index < results.size()) {
            return results.get(index);
        }
        return null;
    }

}
