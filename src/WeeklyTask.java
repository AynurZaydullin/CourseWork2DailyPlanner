import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatability{
    protected WeeklyTask(String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
        super(title, description, taskType, localDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek() == requestedDate.getDayOfWeek();
    }
}
