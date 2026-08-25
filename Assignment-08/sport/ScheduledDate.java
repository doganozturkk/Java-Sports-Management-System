package sport;

import java.io.Serializable;
import java.time.LocalDate;

public class ScheduledDate implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate scheduledDate;

    public ScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    @Override
    public String toString() {
        return scheduledDate != null
                ? scheduledDate.toString()
                : "N/A";
    }
}