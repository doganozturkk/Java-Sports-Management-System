package si.um.opj.ozturk.ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Sports System");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Athletes", new AthletePanel());
        tabs.add("Venues", new VenuePanel());
        tabs.add("Matches", new MatchPanel());
        tabs.add("Events", new EventPanel());

        add(tabs);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}