public class CartesianResultFactory implements ResultFactory {
    @Override
    public Result createResult(double v0, double alpha, double g, double[][] coordinates) {
        return new Result(v0, alpha, g, coordinates);
    }

    @Override
    public String formatResult(Result result) {
        return "Formatted Cartesian result";
    }
}