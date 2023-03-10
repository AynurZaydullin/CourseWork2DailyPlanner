import java.time.LocalDateTime;

public abstract class Task {
    private String title;
    private String description;
    private TaskType taskType;
    private LocalDateTime firstDate;
    private boolean archived;
    private static Integer counter = 1;
    private final Integer id;

    //Создание задачи через класс.
    protected Task(String title, String description, TaskType taskType, LocalDateTime localDateTime) {
        setTitle(title);
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.firstDate = localDateTime;
        this.id = counter++;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public static Integer getCounter() {
        return counter;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Некорректный ввод.");
        } else {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setFirstType(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public static void setCounter(Integer counter) {
        Task.counter = counter;
    }

    public abstract boolean checkOccurrence(LocalDateTime requestedDate);
}
