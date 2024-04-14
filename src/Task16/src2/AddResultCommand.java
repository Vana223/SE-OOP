package src.Task16.src2;

public class AddResultCommand implements Command {
    private Result result;

    public AddResultCommand(double v0, double alpha, double g, double[][] coordinates) {
        this.result = new Result(v0, alpha, g, coordinates);
    }

    @Override
    public void execute() {
        result.saveResult();
    }

    @Override
    public void undo() {
        result.deleteResult();
    }
}
