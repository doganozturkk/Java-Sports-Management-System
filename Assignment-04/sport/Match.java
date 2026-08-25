package sport;

import infrastructure.Venue;

public class Match extends Event implements Competition {

    private Athlete[] athletesList = new Athlete[10];

    public Match(String name, ScheduledDate scheduledDate) {
        super(name, scheduledDate);
    }

    public void addAthleteToMatch(Athlete athlete) throws AddingAthleteException {
        try {
            if (athlete == null) {
                throw new AddingAthleteException();
            }

            if (!athlete.canCompete(this)) {
                throw new AddingAthleteException();
            }

            for (int i = 0; i < athletesList.length; i++) {
                if (athletesList[i] == null) {
                    athletesList[i] = athlete;

                    // 🔥 KRAL FIX: athlete’in ownMatchList’ine EKLE
                    Match[] ownList = athlete.getOwnMatchList();
                    for (int j = 0; j < ownList.length; j++) {
                        if (ownList[j] == null) {
                            ownList[j] = this;
                            break;
                        }
                    }

                    return;
                }
            }

        } catch (AddingAthleteException e) {
            // 🔥 Exception bas ama programı durdurma
            System.out.println("Exception:" + e.getMessage());
        }
    }

    public int getNumberOfAthletesInMatch() {
        int count = 0;
        for (Athlete athlete : athletesList) {
            if (athlete != null) count++;
        }
        return count;
    }

    public boolean athleteExists(String surname) {
        for (Athlete athlete : athletesList) {
            if (athlete != null && athlete.getSurname().equals(surname)) return true;
        }
        return false;
    }

    @Override
    public void checkValidity(Venue venue) {
        Match[] matches = venue.getMatchList();

        for (Match m : matches) {
            if (m == this) {
                System.out.println("Match " + getName() + " can be performed");
                return;
            }
        }

        System.out.println("Match " + getName() + " has not been added to venue");
    }
}