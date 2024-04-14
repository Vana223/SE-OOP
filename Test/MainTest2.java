package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import src.Task16.src2.*;

public class MainTest2 {

    @Test
    public void testUndoCommand() {
        CommandManager commandManager = CommandManager.getInstance();

        // Створення команди на додавання результату
        double v0 = 10;
        double alpha = Math.toRadians(45);
        double g = 9.8;
        double[][] coordinates = new double[16][2];
        for (int i = 0; i < 16; i++) {
            double t = i;
            coordinates[i][0] = v0 * Math.cos(alpha) * t;
            coordinates[i][1] = v0 * Math.sin(alpha) * t - (g * t * t) / 2;
        }
        Command addResultCommand = new AddResultCommand(v0, alpha, g, coordinates);

        // Виконання команди на додавання результату
        commandManager.executeCommand(addResultCommand);

        // Виконання команди скасування
        commandManager.undo();

        // Перевірка, чи був результат видалений
        assertNull(Result.getResult(0));
    }

    @Test
    public void testExecuteMacroCommand() {
        CommandManager commandManager = CommandManager.getInstance();

        // Створення результату для видалення
        double v0 = 10;
        double alpha = Math.toRadians(45);
        double g = 9.8;
        double[][] coordinates = new double[16][2];
        for (int i = 0; i < 16; i++) {
            double t = i;
            coordinates[i][0] = v0 * Math.cos(alpha) * t;
            coordinates[i][1] = v0 * Math.sin(alpha) * t - (g * t * t) / 2;
        }
        Result result = new Result(v0, alpha, g, coordinates);

        // Збереження результату
        result.saveResult();

        // Створення команди на видалення результату
        Command removeResultCommand = new RemoveResultCommand(0);

        // Створення макрокоманди
        CompositeCommandImpl macroCommand = new CompositeCommandImpl();
        macroCommand.addCommand(removeResultCommand);

        // Виконання макрокоманди
        commandManager.executeCommand(macroCommand);

        // Перевірка, чи був результат видалений
        assertNull(Result.getResult(0));
    }
}