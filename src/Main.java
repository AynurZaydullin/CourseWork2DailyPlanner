import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        DailyPlanner.addTask(scanner);
                        break;
//                    case 2:
//                        DailyPlanner.editTask(scanner);
                    case 2:
                        DailyPlanner.deleteTask(scanner);
                        break;
                    case 3:
                        DailyPlanner.getTasksByDay(scanner);
//                    case 5:
//                        DailyPlanner.printArchivedTask();
//                        break;
//                    case 6:
//                        DailyPlanner.getGroupByDate();
//                        break;
                    case 0:
                        break label;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        // todo
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу \n" +
                        "2. Удалить задачу \n" +
                        "3. Получить задачу на указанный день \n" +
                        "0. Выход\n"
        );
    }
}