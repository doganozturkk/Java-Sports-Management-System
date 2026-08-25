package sport;

import infrastructure.Venue;
import java.time.LocalDate;
import java.time.Period;

public class Athlete implements Competition {

    private String name;
    private String surname;
    private int athleteNumber;
    private LocalDate birthDate;
    private SportsDiscipline sportsDiscipline;

    private Match[] ownMatchList = new Match[5];

    public Athlete() { }

    public Athlete(String name, String surname, int athleteNumber, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.athleteNumber = athleteNumber;
        this.birthDate = birthDate;
    }

    // GETTERS
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getAthleteNumber() { return athleteNumber; }
    public LocalDate getBirthDate() { return birthDate; }
    public SportsDiscipline getSportsDiscipline() { return sportsDiscipline; }
    public Match[] getOwnMatchList() { return ownMatchList; }

    // SETTERS
    public void setAthleteNumber(int athleteNumber) {
        this.athleteNumber = athleteNumber;
    }

    public void setSportsDiscipline(SportsDiscipline sportsDiscipline) {
        this.sportsDiscipline = sportsDiscipline;
    }

    // AGE
    public int getAge() {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // COMPETITION CHECK
    public boolean canCompete(Match match) {
        if (birthDate == null || match == null) return false;

        LocalDate matchDate = match.getScheduledDate().getScheduledDate();
        int age = Period.between(birthDate, matchDate).getYears();

        return age <= 15;
    }

    // VALIDITY CHECK
    @Override
    public void checkValidity(Venue venue) {

        Match[] matches = venue.getMatchList();

        for (Match match : matches) {

            if (match != null && match.athleteExists(surname)) {

                boolean alreadyExists = false;

                for (Match m : ownMatchList) {
                    if (m == match) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (!alreadyExists) {
                    for (int i = 0; i < ownMatchList.length; i++) {
                        if (ownMatchList[i] == null) {
                            ownMatchList[i] = match;
                            break;
                        }
                    }
                }
            }
        }
    }

    // DISPLAY
    @Override
    public String toString() {
        return name + " " + surname +
                " | No: " + athleteNumber +
                " | Age: " + getAge() +
                " | Discipline: " +
                (sportsDiscipline != null ? sportsDiscipline : "N/A");
    }
}