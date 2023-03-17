import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatability{
    protected YearlyTask(String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
        super(title, description, taskType, localDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();
    }
}
