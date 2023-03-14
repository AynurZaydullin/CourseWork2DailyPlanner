import java.time.LocalDateTime;

public interface Repeatability {
    LocalDateTime getFirstDate();

    void setTitle(String title);

    boolean checkOccurrence(LocalDateTime atStartOfDay);
}