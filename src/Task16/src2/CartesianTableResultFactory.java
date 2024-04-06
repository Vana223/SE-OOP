package src.Task16.src2;

public class CartesianTableResultFactory implements TableResultFactory {

    @Override
    public String formatResultAsTable(Result result, int columns) {
        StringBuilder sb = new StringBuilder();
        double[][] coordinates = result.getCoordinates();
        int rows = coordinates.length;

        sb.append("Time\tX\tY\n");

        for (int i = 0; i < rows; i++) {
            sb.append(i).append("\t");
            sb.append(coordinates[i][0]).append("\t");
            sb.append(coordinates[i][1]).append("\n");
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