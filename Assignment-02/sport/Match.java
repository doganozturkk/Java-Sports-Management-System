package sport;

public class Match {

    private String name;
    private ScheduledDate scheduledDate;

    private Athlete[] athletesList = new Athlete[10];

    public Match(String name, ScheduledDate scheduledDate) {
        this.name = name;
        this.scheduledDate = scheduledDate;
    }

    public void addAthleteToMatch(Athlete athlete) {

        for (int i = 0; i < athletesList.length; i++) {
            if (athletesList[i] == null) {
                athletesList[i] = athlete;
                return;
            }
        }

    }

    public void removeAthleteFromMatch(Athlete athlete) {

        for (int i = 0; i < athletesList.length; i++) {
            if (athletesList[i] == athlete) {
                athletesList[i] = null;
                return;
            }
        }

    }

    public boolean removeAthleteFromMatch(int athleteNumber) {

        for (int i = 0; i < athletesList.length; i++) {

            if (athletesList[i] != null && athletesList[i].getAthleteNumber() == athleteNumber) {
                athletesList[i] = null;
                return true;
            }

        }

        return false;
    }

    public int getNumberOfAthletesInMatch() {

        int count = 0;

        for (Athlete athlete : athletesList) {
            if (athlete != null) {
                count++;
            }
        }

        return count;
    }

    public boolean athleteExists(String surname) {

        for (Athlete athlete : athletesList) {

            if (athlete != null && athlete.getSurname().equals(surname)) {
                return true;
            }

        }

        return false;
    }

    @Override
    public String toString() {
        return "Match:" + name + "," + scheduledDate;
    }

}