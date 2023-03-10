import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DailyPlanner {
    private static final Map<Integer, Repeatability> actualTask = new HashMap<>();
    public static void addTask(Scanner scanner)   {
        scanner.nextLine();
        System.out.println("Введите название задачи: ");
        String title = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Введите повторяемость задачи: однократная - 0, " +
                "ежедневная: - 1.");
        TaskType taskType = TaskType.values()[scanner.nextInt()];

        System.out.println("Введите дату в формате: dd.MM.yyyy");
        String data = scanner.nextLine();
        LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        DailyTask dailyTask = new DailyTask(title, description, taskType, eventDate);
    }
}
