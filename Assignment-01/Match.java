public class Match {

    private String title;
    private ScheduledDate scheduledDate;

    public Match(String title, ScheduledDate scheduledDate) {
        this.title = title;
        this.scheduledDate = scheduledDate;
    }

    public String getTitle() {
        return title;
    }

    public ScheduledDate getScheduledDate() {
        return scheduledDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScheduledDate(ScheduledDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public boolean canCompete(Athlete athlete) {

        int athleteYear = athlete.getBirthDate().getYear();
        int matchYear = scheduledDate.getScheduledDate().getYear();

        int age = matchYear - athleteYear;

        return age >= 14 && age <= 18;
    }
}