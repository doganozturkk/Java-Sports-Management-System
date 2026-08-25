package sport;

import infrastructure.RunningTrack;
import infrastructure.Stadium;
import infrastructure.Venue;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class Athlete
        implements Competition, Runnable, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private int athleteNumber;
    private LocalDate birthDate;
    private SportsDiscipline sportsDiscipline;

    private Match[] ownMatchList = new Match[5];

    // RACE
    private long currentTime;
    private static long bestTime = Long.MAX_VALUE;
    private static Athlete winner;
    private static final Object lock = new Object();
    public static Stadium stadium;

    // STORE FINISH RESULTS
    private static final ArrayList<Athlete> results =
            new ArrayList<>();

    public Athlete() {}

    public Athlete(
            String name,
            String surname,
            int athleteNumber,
            LocalDate birthDate
    ) {

        this.name = name;
        this.surname = surname;
        this.athleteNumber = athleteNumber;
        this.birthDate = birthDate;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public SportsDiscipline getSportsDiscipline() {
        return sportsDiscipline;
    }

    public Match[] getOwnMatchList() {
        return ownMatchList;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public static long getBestTime() {
        return bestTime;
    }

    public static Athlete getWinner() {
        return winner;
    }

    public static ArrayList<Athlete> getResults() {
        return results;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAthleteNumber(int athleteNumber) {
        this.athleteNumber = athleteNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSportsDiscipline(
            SportsDiscipline sportsDiscipline
    ) {

        this.sportsDiscipline = sportsDiscipline;
    }

    // RESET RACE
    public static void resetRace() {

        bestTime = Long.MAX_VALUE;

        winner = null;

        results.clear();
    }

    // AGE
    public int getAge() {

        if (birthDate == null) {
            return 0;
        }

        return Period.between(
                birthDate,
                LocalDate.now()
        ).getYears();
    }

    // MATCH CONTROL
    public boolean canCompete(Match match) {

        if (birthDate == null || match == null) {
            return false;
        }

        LocalDate date =
                match.getScheduledDate()
                        .getScheduledDate();

        int age =
                Period.between(
                        birthDate,
                        date
                ).getYears();

        return age >= 15;
    }

    // THREAD
    @Override
    public void run() {

        if (sportsDiscipline
                != SportsDiscipline.ATHLETICS) {

            return;
        }

        try {

            RunningTrack track = null;

            while (track == null) {
                track = stadium.prepareRaceTrack();
            }

            currentTime = 0;

            System.out.println(
                    "🏃 Athlete #" +
                            athleteNumber +
                            " started running on Track #" +
                            track.getTrackNumber()
            );

            Random random = new Random();

            for (int i = 0; i < 10; i++) {

                int delay =
                        80 + random.nextInt(120);

                Thread.sleep(delay);

                currentTime += delay;
            }

            synchronized (lock) {

                if (currentTime < bestTime) {

                    bestTime = currentTime;

                    winner = this;
                }

                results.add(this);
            }

            track.setInUse(false);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    // SORT RESULTS
    public static void printResults() {

        results.sort(
                Comparator.comparingLong(
                        Athlete::getCurrentTime
                )
        );

        System.out.println();

        System.out.println(
                " 🏁 FINAL RESULTS 🏁 "
        );

        int place = 1;

        for (Athlete athlete : results) {

            System.out.println(
                    place + ". Athlete #" +
                            athlete.getAthleteNumber() +
                            " (" +
                            athlete.getName() +
                            " " +
                            athlete.getSurname() +
                            ") → " +
                            athlete.getCurrentTime() +
                            " ms"
            );

            place++;
        }

        System.out.println();

        if (winner != null) {

            System.out.println(
                    "🏆 WINNER: Athlete #" +
                            winner.getAthleteNumber() +
                            " (" +
                            winner.getName() +
                            " " +
                            winner.getSurname() +
                            ")"
            );

            System.out.println(
                    "🏆 BEST TIME: " +
                            bestTime +
                            " ms"
            );
        }

        System.out.println();
    }

    // VALIDITY
    @Override
    public void checkValidity(Venue venue) {}

    // EQUALS
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Athlete)) {
            return false;
        }

        Athlete athlete = (Athlete) o;

        return athleteNumber ==
                athlete.athleteNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(athleteNumber);
    }

    // DISPLAY
    @Override
    public String toString() {

        return name +
                " " +
                surname +
                " | #" +
                athleteNumber +
                " | " +
                sportsDiscipline;
    }
}