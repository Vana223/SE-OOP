/**
 * Клас для агрегування результатів 16-річних подання цілісних значень координат
 */

package src.Task16.src2;

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
