package sport;

import java.time.LocalDate;

public class Athlete {

    private String name;
    private String surname;
    private int athleteNumber;
    private LocalDate birthDate;

    // Default constructor
    public Athlete() {
    }

    // Constructor with parameters
    public Athlete(String name, String surname, int athleteNumber, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.athleteNumber = athleteNumber;
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    @Override
    public String toString() {
        return "Athlete:" + name + "," + surname + "," + athleteNumber + "," + birthDate;
    }
}