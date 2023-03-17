import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatability{
    protected MonthlyTask(String title, String description, TaskType taskType, LocalDateTime localDateTime)throws WrongInputException {
        super(title, description, taskType, localDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfMonth() == (requestedDate.getDayOfMonth());
    }
}
