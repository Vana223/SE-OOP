import java.util.Scanner;

public class demo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть кількість стовпців для таблиці: ");
        int columns = scanner.nextInt();

        ResultFactory resultFactory;
        System.out.println("Виберіть тип фабрики (1 - Cartesian, 2 - Polar): ");
        int factoryType = scanner.nextInt();
        if (factoryType == 1) {
            resultFactory = new CartesianTableResultFactory();
        } else {
            resultFactory = new PolarTableResultFactory();
        }

        solver solver = new solver(10, Math.toRadians(45), 9.8);
        Result result = solver.solve(16);

        String formattedResult = resultFactory.formatResultAsTable(result, columns);
        System.out.println("Formatted Result Table:");
        System.out.println(formattedResult);

        scanner.close();
    }
}