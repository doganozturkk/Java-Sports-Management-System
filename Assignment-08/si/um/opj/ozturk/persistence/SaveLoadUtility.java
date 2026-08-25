package si.um.opj.ozturk.persistence;

import infrastructure.Venue;
import si.um.opj.ozturk.ui.DataStore;
import sport.*;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SaveLoadUtility {

    private static final String FILE_NAME = "sports_data.gz";

    // SAVE
    public static void saveData() {

        try (

                ObjectOutputStream oos =
                        new ObjectOutputStream(
                                new GZIPOutputStream(
                                        new FileOutputStream(FILE_NAME)
                                )
                        )

        ) {

            oos.writeObject(DataStore.athletes);
            oos.writeObject(DataStore.events);
            oos.writeObject(DataStore.venues);
            oos.writeObject(DataStore.matches);

            System.out.println("[SUCCESS] Data saved.");

        } catch (IOException e) {

            System.out.println("[ERROR] Save failed.");
            e.printStackTrace();
        }
    }

    // LOAD
    @SuppressWarnings("unchecked")
    public static void loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("[INFO] No save file found.");
            return;
        }

        try (

                ObjectInputStream ois =
                        new ObjectInputStream(
                                new GZIPInputStream(
                                        new FileInputStream(FILE_NAME)
                                )
                        )

        ) {

            DataStore.athletes.clear();
            DataStore.events.clear();
            DataStore.venues.clear();
            DataStore.matches.clear();

            DataStore.athletes.addAll(
                    (List<Athlete>) ois.readObject()
            );

            DataStore.events.addAll(
                    (List<Event>) ois.readObject()
            );

            DataStore.venues.addAll(
                    (List<Venue>) ois.readObject()
            );

            DataStore.matches.addAll(
                    (List<Match>) ois.readObject()
            );

            System.out.println("[SUCCESS] Data loaded.");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("[ERROR] Load failed.");
            e.printStackTrace();
        }
    }
}