package sport;

public class Training extends Event {

    private String trainerName;

    public Training(String name, ScheduledDate scheduledDate, String trainerName) {
        super(name, scheduledDate);
        this.trainerName = trainerName;
    }

    @Override
    public String toString() {
        return super.toString() + "," + trainerName;
    }
}