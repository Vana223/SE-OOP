package src.Task16.src2;

import java.util.*;

public class demo3 {
    private static CommandManager commandManager = CommandManager.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addResultCommand(scanner);
                    break;
                case 2:
                    removeResultCommand(scanner);
                    break;
                case 3:
                    undoCommand();
                    break;
                case 4:
                    executeMacroCommand(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Result");
        System.out.println("2. Remove Result");
        System.out.println("3. Undo");
        System.out.println("4. Execute Macro Command");
        System.out.println("5. Exit");
        System.out.println("Enter your choice:");
    }

    private static void addResultCommand(Scanner scanner) {
        System.out.println("Enter data for adding result:");
        // Get data from user
        double v0 = scanner.nextDouble();
        double alpha = scanner.nextDouble();
        double g = scanner.nextDouble();
        int timeSteps = scanner.nextInt();

        double[][] coordinates = new double[timeSteps][2];
        for (int i = 0; i < timeSteps; i++) {
            double t = i;
            double x = v0 * Math.cos(alpha) * t;
            double y = v0 * Math.sin(alpha) * t - (g * t * t) / 2;

            coordinates[i][0] = x;
            coordinates[i][1] = y;
        }

        Command command = new AddResultCommand(v0, alpha, g, coordinates);
        commandManager.executeCommand(command);
    }

    private static void removeResultCommand(Scanner scanner) {
        System.out.println("Enter index of result to remove:");
        int index = scanner.nextInt();
        Command command = new RemoveResultCommand(index);
        commandManager.executeCommand(command);
    }

    private static void undoCommand() {
        commandManager.undo();
    }

    private static void executeMacroCommand(Scanner scanner) {
        System.out.println("Executing Macro Command...");
        CompositeCommandImpl macroCommand = new CompositeCommandImpl();

        System.out.println("Enter number of commands to include in macro:");
        int numCommands = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 0; i < numCommands; i++) {
            System.out.println("Enter command (1 - Add Result, 2 - Remove Result):");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    macroCommand.addCommand(createAddResultCommand(scanner));
                    break;
                case 2:
                    macroCommand.addCommand(createRemoveResultCommand(scanner));
                    break;
                default:
                    System.out.println("Invalid command choice.");
            }
        }

        commandManager.executeCommand(macroCommand);
    }

    private static Command createAddResultCommand(Scanner scanner) {
        System.out.println("Enter data for adding result:");
        // Get data from user
        double v0 = scanner.nextDouble();
        double alpha = scanner.nextDouble();
        double g = scanner.nextDouble();
        int timeSteps = scanner.nextInt();

        double[][] coordinates = new double[timeSteps][2];
        for (int i = 0; i < timeSteps; i++) {
            double t = i;
            double x = v0 * Math.cos(alpha) * t;
            double y = v0 * Math.sin(alpha) * t - (g * t * t) / 2;

            coordinates[i][0] = x;
            coordinates[i][1] = y;
        }

        return new AddResultCommand(v0, alpha, g, coordinates);
    }

    private static Command createRemoveResultCommand(Scanner scanner) {
        System.out.println("Enter index of result to remove:");
        int index = scanner.nextInt();
        return new RemoveResultCommand(index);
    }
}