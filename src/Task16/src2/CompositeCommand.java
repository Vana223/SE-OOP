package src.Task16.src2;

interface CompositeCommand extends Command {
    void addCommand(Command command);
}
