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

    public String getSurname() { return surname; }

    public SportsDiscipline getSportsDiscipline() { return sportsDiscipline; }

    public void setSportsDiscipline(SportsDiscipline sportsDiscipline) { this.sportsDiscipline = sportsDiscipline; }

    public Match[] getOwnMatchList() { return ownMatchList; }

    public boolean canCompete(Match match) {
        if (birthDate == null || match == null) return false;
        LocalDate matchDate = match.getScheduledDate().getScheduledDate();
        int age = Period.between(birthDate, matchDate).getYears();
        return age <= 15; // hocanın istediği genç atlet sınırı
    }

    @Override
    public void checkValidity(Venue venue) {

        // 🔥 TÜM MATCH'LERİ KONTROL ET (venue bağımlılığını kaldırıyoruz)
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

    @Override
    public String toString() {
        return "Athlete:" + name + "," + surname + "," + athleteNumber + "," + birthDate + "," + sportsDiscipline;
    }
}