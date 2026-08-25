package si.um.opj.ozturk.ui;

import si.um.opj.ozturk.persistence.SaveLoadUtility;

public class Main {

    public static void main(String[] args) {

        // LOAD SAVED DATA
        SaveLoadUtility.loadData();

        // START GUI
        new MainFrame();

        // SAVE ON EXIT
        Runtime.getRuntime().addShutdownHook(
                new Thread(SaveLoadUtility::saveData)
        );
    }
}