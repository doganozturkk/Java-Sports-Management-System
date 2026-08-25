package sport;

public class Training extends Event {

    private static final long serialVersionUID = 1L;

    private String trainerName;

    public Training(String name,
                    ScheduledDate scheduledDate,
                    String trainerName) {

        super(name, scheduledDate);

        this.trainerName = trainerName;
    }

    @Override
    public String toString() {

        return super.toString() +
                ", " +
                trainerName;
    }
}