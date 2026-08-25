package sport;

import infrastructure.Venue;

import java.io.Serializable;
import java.util.Objects;

public class Match extends Event implements Competition, Serializable {

    private static final long serialVersionUID = 1L;

    private Athlete[] athletesList = new Athlete[10];
    private Venue venue;

    public Match(String name, ScheduledDate scheduledDate) {
        super(name, scheduledDate);
    }

    // VENUE ASSIGNMENT
    public void setVenue(Venue newVenue) throws AddingMatchException {

        if (newVenue == null) {
            throw new AddingMatchException("Venue is null!");
        }

        if (this.venue != null && this.venue != newVenue) {
            throw new AddingMatchException(
                    "This match is already assigned to a venue!"
            );
        }

        this.venue = newVenue;
    }

    public Venue getVenue() {
        return venue;
    }

    // ADD ATHLETE
    public void addAthleteToMatch(Athlete athlete) throws AddingAthleteException {

        if (athlete == null) {
            throw new AddingAthleteException("Athlete is null!");
        }

        if (!athlete.canCompete(this)) {
            throw new AddingAthleteException("Athlete cannot compete!");
        }

        for (Athlete a : athletesList) {
            if (a != null && a.equals(athlete)) {
                throw new AddingAthleteException("Athlete already added!");
            }
        }

        for (int i = 0; i < athletesList.length; i++) {

            if (athletesList[i] == null) {

                athletesList[i] = athlete;

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

        throw new AddingAthleteException("Match is full!");
    }

    // COUNT ATHLETES
    public int getNumberOfAthletesInMatch() {

        int count = 0;

        for (Athlete athlete : athletesList) {
            if (athlete != null) {
                count++;
            }
        }

        return count;
    }

    // SEARCH ATHLETE
    public boolean athleteExists(String surname) {

        for (Athlete athlete : athletesList) {

            if (athlete != null &&
                    athlete.getSurname().equals(surname)) {

                return true;
            }
        }

        return false;
    }

    public void setName(String name) {
        super.setName(name);
    }

    public void setScheduledDate(ScheduledDate scheduledDate) {
        super.setScheduledDate(scheduledDate);
    }

    // VALIDITY CHECK
    @Override
    public void checkValidity(Venue venue) {

        if (venue == null) {
            System.out.println("Venue is null!");
            return;
        }

        for (Match m : venue.getMatchList()) {

            if (m == this) {

                System.out.println(
                        "Match " + getName() +
                                " is assigned to venue " +
                                venue.getName()
                );

                return;
            }
        }

        System.out.println(
                "Match " + getName() +
                        " is NOT assigned to this venue!"
        );
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Match)) {
            return false;
        }

        Match match = (Match) o;

        return getName().equals(match.getName()) &&
                getScheduledDate().getScheduledDate()
                        .equals(match.getScheduledDate().getScheduledDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                getName(),
                getScheduledDate().getScheduledDate()
        );
    }

    @Override
    public String toString() {

        return "Match: " + getName() +
                " | Date: " + getScheduledDate().getScheduledDate() +
                " | Athletes: " + getNumberOfAthletesInMatch() +
                " | Venue: " +
                (venue != null ? venue.getName() : "N/A");
    }
}