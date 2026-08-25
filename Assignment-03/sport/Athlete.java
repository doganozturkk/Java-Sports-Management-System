package sport;

import java.time.LocalDate;

public class Athlete {

    private String name;
    private String surname;
    private int athleteNumber;
    private LocalDate birthDate;
    private SportsDiscipline sportsDiscipline;

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

    public SportsDiscipline getSportsDiscipline() {
        return sportsDiscipline;
    }

    public void setSportsDiscipline(SportsDiscipline sportsDiscipline) {
        this.sportsDiscipline = sportsDiscipline;
    }

    @Override
    public String toString() {
        return "Athlete:" + name + "," + surname + "," + athleteNumber + "," + birthDate + "," + sportsDiscipline;
    }
}