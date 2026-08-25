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

    // VENUE
    public void setVenue(Venue newVenue) throws AddingMatchException {

        if (newVenue == null) {
            throw new AddingMatchException("Venue is null!");
        }

        if (this.venue != null && this.venue != newVenue) {
            throw new AddingMatchException("Already assigned!");
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
            throw new AddingAthleteException("Cannot compete!");
        }

        for (Athlete a : athletesList) {
            if (a != null && a.equals(athlete)) {
                throw new AddingAthleteException("Already added!");
            }
        }

        for (int i = 0; i < athletesList.length; i++) {

            if (athletesList[i] == null) {

                athletesList[i] = athlete;

                // 🔥 SAFE ACCESS
                Match[] list = athlete.getOwnMatchList();

                for (int j = 0; j < list.length; j++) {
                    if (list[j] == null) {
                        list[j] = this;
                        break;
                    }
                }

                return;
            }
        }

        throw new AddingAthleteException("Match full!");
    }

    public int getNumberOfAthletesInMatch() {
        int count = 0;
        for (Athlete a : athletesList) {
            if (a != null) count++;
        }
        return count;
    }

    public boolean athleteExists(String surname) {
        for (Athlete a : athletesList) {
            if (a != null && a.getSurname().equals(surname)) {
                return true;
            }
        }
        return false;
    }

    // SETTERS (GUI SUPPORT)
    public void setName(String name) {
        super.setName(name);
    }

    public void setScheduledDate(ScheduledDate scheduledDate) {
        super.setScheduledDate(scheduledDate);
    }

    // CHECK
    @Override
    public void checkValidity(Venue venue) {}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Match)) return false;

        Match m = (Match) o;

        return getName().equals(m.getName()) &&
                getScheduledDate().getScheduledDate()
                        .equals(m.getScheduledDate().getScheduledDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getScheduledDate().getScheduledDate());
    }

    @Override
    public String toString() {
        return "Match: " + getName() +
                " | Athletes: " + getNumberOfAthletesInMatch() +
                " | Venue: " + (venue != null ? venue.getName() : "N/A");
    }
}