import java.time.LocalDateTime;

public interface Repeatability {
    LocalDateTime getFirstDate();

    boolean checkOccurrence(LocalDateTime atStartOfDay);
}