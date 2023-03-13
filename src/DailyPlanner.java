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
        TaskType taskType = TaskType.values()[scanner.nextInt()];

        System.out.println("Введите дату в формате: dd.MM.yyyy HH:mm");
        String data = scanner.nextLine();
        LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        DailyTask dailyTask = new DailyTask(title, description, taskType, eventDate);
        int occurrence = scanner.nextInt();
        Repeatability task = null;
        task = createTask(occurrence, title, description, taskType, eventDate);
        System.out.println(eventDate);
    }

        private static Repeatability createTask(int occurrence, String title, String description, TaskType taskType, LocalDateTime localDateTime)  { //throws WrongInputException
         switch (occurrence){
            case 0 :
                OnceTask onceTask = new OnceTask(title, description, taskType, localDateTime);
                actualTask.put(onceTask.getId(), onceTask);
                System.out.println();
            break;
            case 1:
//                DailyTask task = new DailyTask(title, description, taskType, localDateTime);
//                actualTask.put(task.getId(), task);
                System.out.println("1");

                break;
//            }
            default : ;
            break;
        };
    }

    public static void printTask() {
        System.out.print(dailyTask);
    }

    private static List<Repeatability> findTasksByDate(LocalDate date) {
        List<Repeatability> tasks = new ArrayList<>();
        for (Repeatability task : actualTask.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
