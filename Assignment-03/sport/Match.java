package sport;

public class Match extends Event {

    private Athlete[] athletesList = new Athlete[10];

    public Match(String name, ScheduledDate scheduledDate) {
        super(name, scheduledDate);
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

            if (athletesList[i] != null &&
                    athletesList[i].getAthleteNumber() == athleteNumber) {

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
}