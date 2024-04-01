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
