package sport;

public class Event {

    private String name;
    private ScheduledDate scheduledDate;

    public Event(String name, ScheduledDate scheduledDate) {
        this.name = name;
        this.scheduledDate = scheduledDate;
    }

    public String getName() {
        return name;
    }

    public ScheduledDate getScheduledDate() {
        return scheduledDate;
    }

    @Override
    public String toString() {
        return "Event:" + name + "," + scheduledDate;
    }
}