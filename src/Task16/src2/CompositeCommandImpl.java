package src.Task16.src2;

import java.util.*;

public class CompositeCommandImpl implements CompositeCommand {
    private List<Command> commands = new ArrayList<>();

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }
}