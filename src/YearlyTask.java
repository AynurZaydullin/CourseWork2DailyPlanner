import java.time.LocalDateTime;

public class YearlyTask extends Task{
    protected YearlyTask(String title, String description, TaskType taskType, LocalDateTime localDateTime) {
        super(title, description, taskType, localDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();
    }
}
