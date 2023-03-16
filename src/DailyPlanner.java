import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DailyPlanner {
    private static final Map<Integer, Repeatability> actualTask = new HashMap<>();

    public static void addTask(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название задачи: ");
        String title = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Введите повторяемость задачи: однократная - 0, " +
                "ежедневная: - 1.");
        int occurrence = scanner.nextInt();
        System.out.println("Введите тип задачи:");
        TaskType taskType = TaskType.values()[scanner.nextInt()];
        System.out.println("Введите дату в формате: dd.MM.yyyy HH:mm");
        String data = scanner.nextLine();
        LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        createTask(occurrence, title, description, taskType, eventDate);
        System.out.println(eventDate);
    }

    private static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurance) {
        LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        Repeatability task = null;
        createTask(occurance, title, description, taskType, eventDate);
    }
        private static void createTask(int occurrence, String title, String description, TaskType taskType, LocalDateTime localDateTime)  { //throws WrongInputException
        if (occurrence == 0) {
            OnceTask onceTask = new OnceTask(title, description, taskType, localDateTime);
            actualTask.put(onceTask.getId(), onceTask);
        }
        else if(occurrence == 1) {
            DailyTask task = new DailyTask(title, description, taskType, localDateTime);
            actualTask.put(task.getId(), task);
            System.out.println("1");
        }
        }

//    public static void printTask() {
//        System.out.print(dailyTask);
//    }

    public static void getTasksByDay(Scanner scanner) {

        System.out.println("Введите дату в формате: dd.MM.yyyy");
        String date = scanner.next();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
        List<Repeatability> foundEvents = findTasksByDate(requestedDate);
        System.out.println("События на " + requestedDate + ":");
        for (Repeatability task : foundEvents) {
            System.out.println(task);
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }
    public static List<Repeatability> findTasksByDate(LocalDate date) {
        List<Repeatability> tasks = new ArrayList<>();
        for (Repeatability task : actualTask.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
