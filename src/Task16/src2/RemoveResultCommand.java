package src.Task16.src2;

public class RemoveResultCommand implements Command {
    private int index;
    private Result removedResult;

    public RemoveResultCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        removedResult = Result.getResult(index);
        removedResult.deleteResult();
    }

    @Override
    public void undo() {
        removedResult.saveResult();
    }
}
