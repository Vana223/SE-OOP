package src.Task16.src2;

public class PolarTableResultFactory implements TableResultFactory {
    // Попередні методи

    @Override
    public String formatResultAsTable(Result result, int columns) {
        StringBuilder sb = new StringBuilder();
        double[][] coordinates = result.getCoordinates();
        int rows = coordinates.length;

        sb.append("Time\tR\tTheta\n");

        for (int i = 0; i < rows; i++) {
            double x = coordinates[i][0];
            double y = coordinates[i][1];
            double r = Math.sqrt(x * x + y * y);
            double theta = Math.atan2(y, x);
            sb.append(i).append("\t");
            sb.append(r).append("\t");
            sb.append(theta).append("\n");
        }

        return sb.toString();
    }

    @Override
    public Result createResult(double v0, double alpha, double g, double[][] coordinates) {
        throw new UnsupportedOperationException("Unimplemented method 'createResult'");
    }

    @Override
    public String formatResult(Result result) {
        throw new UnsupportedOperationException("Unimplemented method 'formatResult'");
    }
}