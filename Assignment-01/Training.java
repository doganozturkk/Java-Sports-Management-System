public class Training {

    private String title;
    private ScheduledDate scheduledDate;
    private String trainerName;

    public Training(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ScheduledDate getScheduledDate() {
        return scheduledDate;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScheduledDate(ScheduledDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}