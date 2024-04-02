public class PolarResultFactory implements ResultFactory {
    @Override
    public Result createResult(double v0, double alpha, double g, double[][] coordinates) {
        return new Result(v0, alpha, g, coordinates);
    }
}
