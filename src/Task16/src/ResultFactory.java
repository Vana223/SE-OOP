public interface ResultFactory {
    Result createResult(double v0, double alpha, double g, double[][] coordinates);
    String formatResult(Result result);
    String formatResultAsTable(Result result, int columns);
}