package src.Task16.src2;

import java.util.*;

public class CommandManager {
    private static CommandManager instance;
    private Deque<Command> commandStack = new ArrayDeque<>();

    private CommandManager() {}

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            command.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }
}
