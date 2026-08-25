package sport;

import java.time.LocalDate;

public class ScheduledDate {

    private LocalDate scheduledDate;

    public ScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    @Override
    public String toString() {
        return scheduledDate != null ? scheduledDate.toString() : "N/A";
    }
}
