import java.time.LocalDateTime;

public class DailyTask extends Task implements Repeatability{
    protected DailyTask(String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
        super(title, description, taskType, localDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
