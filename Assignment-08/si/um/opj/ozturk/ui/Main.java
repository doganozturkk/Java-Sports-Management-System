package si.um.opj.ozturk.ui;

import si.um.opj.ozturk.persistence.SaveLoadUtility;
import sport.LauncherThread;

public class Main {

    public static void main(String[] args) {

        // LOAD DATA
        SaveLoadUtility.loadData();

        // START GUI
        new MainFrame();

        // START RACE THREADS
        LauncherThread.startRace();

        // SAVE ON EXIT
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {

                    SaveLoadUtility.saveData();

                    System.out.println(
                            "[INFO] Application closed. Data saved."
                    );
                })
        );
    }
}