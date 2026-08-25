package sport;

import infrastructure.Stadium;
import infrastructure.Venue;
import si.um.opj.ozturk.ui.DataStore;

import java.util.ArrayList;
import java.util.Comparator;

public class LauncherThread {

    public static void startRace() {

        Athlete.resetRace();

        Stadium stadium = null;

        // FIND STADIUM
        for (Venue venue : DataStore.venues) {

            if (venue instanceof Stadium) {

                stadium = (Stadium) venue;

                break;
            }
        }

        if (stadium == null) {

            System.out.println();
            System.out.println("❌ NO STADIUM FOUND!");
            System.out.println();

            return;
        }

        Athlete.stadium = stadium;

        ArrayList<Athlete> athleticsAthletes =
                new ArrayList<>();

        // ONLY ATHLETICS
        for (Athlete athlete : DataStore.athletes) {

            if (athlete.getSportsDiscipline()
                    == SportsDiscipline.ATHLETICS) {

                athleticsAthletes.add(athlete);
            }
        }

        // SORT BY NUMBER
        athleticsAthletes.sort(
                Comparator.comparingInt(
                        Athlete::getAthleteNumber
                )
        );

        if (athleticsAthletes.isEmpty()) {

            System.out.println();
            System.out.println("❌ NO ATHLETICS ATHLETES!");
            System.out.println();

            return;
        }

        ArrayList<Thread> threads =
                new ArrayList<>();

        for (Athlete athlete : athleticsAthletes) {

            threads.add(new Thread(athlete));
        }

        System.out.println();

        System.out.println("🏁 100M RACE COMPETITION 🏁");

        System.out.println(
                " Stadium : " +
                        stadium.getName()
        );

        System.out.println(
                " Tracks  : " +
                        stadium.getTrackCount()
        );

        System.out.println(
                " Athletes: " +
                        athleticsAthletes.size()
        );

        System.out.println();

        // START THREADS
        for (Thread t : threads) {
            t.start();
        }

        // WAIT THREADS
        for (Thread t : threads) {

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // PRINT SORTED RESULTS
        Athlete.printResults();
    }
}