public class Main {

    public static void main(String[] args) {

        System.out.println("Кількість аргументів: " + args.length);

        if (args.length != 3) {
            System.out.println("Помилка: потрібно 3 аргументи.");
            System.out.println("Формат: java Calculator <число1> <операція> <число2>");
            System.exit(1);
        }

        // Виводимо отримані аргументи
        System.out.println("Аргумент 1 (число 1): " + args[0]);
        System.out.println("Аргумент 2 (операція): " + args[1]);
        System.out.println("Аргумент 3 (число 2): " + args[2]);

        double num1 = Double.parseDouble(args[0]);
        String operation = args[1];
        double num2 = Double.parseDouble(args[2]);

        System.out.println("Перетворене число 1: " + num1);
        System.out.println("Перетворене число 2: " + num2);

        double result = 0;

        switch (operation) {
            case "+":
                result = num1 + num2;
                System.out.println("Виконується додавання: " + num1 + " + " + num2);
                break;

            case "-":
                result = num1 - num2;
                System.out.println("Виконується віднімання: " + num1 + " - " + num2);
                break;

            case "*":
                result = num1 * num2;
                System.out.println("Виконується множення: " + num1 + " * " + num2);
                break;

            case "/":
                if (num2 == 0) {
                    System.out.println("Помилка: ділення на нуль!");
                    System.exit(1);
                }
                result = num1 / num2;
                System.out.println("Виконується ділення: " + num1 + " / " + num2);
                break;

            default:
                System.out.println("Невідома операція: " + operation);
                System.exit(1);
        }

        System.out.println("Результат: " + result);
    }
}
