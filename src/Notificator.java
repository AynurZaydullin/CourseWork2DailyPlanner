import java.time.LocalDate;
import java.util.List;

public class Notificator implements Runnable{

    @Override
    public void run() {
        int i = 30;
        while (i > 0) {
            try {
                i--;
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Repeatability> tasksByDate = DailyPlanner.findTasksByDate(LocalDate.now());
            if (!tasksByDate.isEmpty()) {
                System.out.println("У тебя есть задачи на сегодня");
                System.out.println(tasksByDate);
            }
        }
    }
}
