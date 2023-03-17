import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DailyPlanner {
    private static final Map<Integer, Repeatability> actualTasks = new HashMap<>();
    private static final Map<Integer, Repeatability> archivedTasks = new HashMap<>();

    public static void addTask(Scanner scanner) {
            try {
                scanner.nextLine();
                System.out.println("Введите название задачи: ");
                String title = ValidateUtils.checkString(scanner.nextLine());
                System.out.println("Введите описание задачи: ");
                String description = ValidateUtils.checkString(scanner.nextLine());
                System.out.println("Введите тип задачи: 0 - Рабочая 1 - Личная");
                TaskType taskType = TaskType.values()[scanner.nextInt()];
                System.out.println("Введите повторяемость задачи: однократная - 0, ежедневная - 1, Ежемесячная - 2, Ежегодная - 4");
                int occurrence = scanner.nextInt();
                System.out.println("Введите дату в формате: dd.MM.yyyy HH:mm");
                scanner.nextLine();
                createEvent(scanner, title, description, taskType, occurrence);
                System.out.println("Для выхода нажмите Enter\n");
                scanner.nextLine();
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        }

    private static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurrence) {
        LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            try {
                    try {
                        if (occurrence == 0) {
                            OnceTask onceTask = new OnceTask(title, description, taskType, eventDate);
                            actualTasks.put(onceTask.getId(), onceTask);
                            System.out.println("Создана задача " + onceTask);
                        } else if (occurrence == 1) {
                            DailyTask dailyTask = new DailyTask(title, description, taskType, eventDate);
                            actualTasks.put(dailyTask.getId(), dailyTask);
                            System.out.println("Создана задача " + dailyTask);
                        } else if (occurrence == 2) {
                            WeeklyTask weeklyTask = new WeeklyTask(title, description, taskType, eventDate);
                            actualTasks.put(weeklyTask.getId(), weeklyTask);
                        } else if (occurrence == 3) {
                            MonthlyTask monthlyTask = new MonthlyTask(title, description, taskType, eventDate);
                            actualTasks.put(monthlyTask.getId(), monthlyTask);
                        } else if (occurrence == 4) {
                            YearlyTask yearlyTask = new YearlyTask(title, description, taskType, eventDate);
                            actualTasks.put(yearlyTask.getId(), yearlyTask);
                        } else {
                            OnceTask onceTask = null;
                            DailyTask dailyTask = null;
                            WeeklyTask monthlyTask = null;
                            YearlyTask yearlyTask = null;
                        }
                    }
                    catch(WrongInputException e) {
                        System.out.println(e.getMessage());
                     }
            }
            catch (DateTimeParseException e) {
                System.out.println("Проверьте формат dd.MM.yyyy HH:mm и попробуйте ещё раз.");
                createEvent(scanner, title, description, taskType, occurrence);
            }
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату в формате: dd.MM.yyyy");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
            List<Repeatability> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Repeatability task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e){
            System.out.println("Проверьте формат даты dd.MM.yyyy и попробуйте ещё раз.");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }
    public static List<Repeatability> findTasksByDate(LocalDate date) {
        List<Repeatability> tasks = new ArrayList<>();
        for (Repeatability task : actualTasks.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи \n");
        printActualTasks();
        System.out.println("Для удаления введите Id задачи \n");
        int id = scanner.nextInt();
        if (actualTasks.containsKey(id)) {
            Repeatability removedTask = actualTasks.get(id);
//            removedTask.setArcheved(true);
            archivedTasks.put(id, removedTask);
            System.out.println("Задача " + id + " удалена\n");
        } else {
            System.out.println("Такой задачи не существует\n");
        }
    }


    private static void printActualTasks(){
        for (Repeatability task : actualTasks.values()) {
            System.out.println(task);
        }
    }

}
