package si.um.opj.ozturk.ui;

import infrastructure.Venue;
import sport.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MatchPanel extends JPanel {

    private DefaultListModel<Match> model =
            new DefaultListModel<>();

    private JList<Match> list =
            new JList<>(model);

    private JComboBox<Athlete> athleteBox =
            new JComboBox<>();

    private JComboBox<Venue> venueBox =
            new JComboBox<>();

    public MatchPanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel =
                new JPanel(new GridLayout(2,2,5,5));

        JTextField nameField =
                new JTextField();

        JTextField dateField =
                new JTextField();

        topPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Create / Update Match"
                )
        );

        topPanel.add(new JLabel("Match Name"));
        topPanel.add(nameField);

        topPanel.add(new JLabel("Date"));
        topPanel.add(dateField);

        JScrollPane scrollPane =
                new JScrollPane(list);

        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(
                new BoxLayout(
                        rightPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JButton createBtn =
                new JButton("Create Match");

        JButton updateBtn =
                new JButton("Update Match");

        JButton deleteBtn =
                new JButton("Delete Match");

        JButton refreshBtn =
                new JButton("Refresh");

        JButton addAthleteBtn =
                new JButton("Add Athlete → Match");

        JButton addVenueBtn =
                new JButton("Add Match → Venue");

        Dimension size =
                new Dimension(200,30);

        for (JComponent c : new JComponent[]{
                createBtn,
                updateBtn,
                deleteBtn,
                refreshBtn,
                athleteBox,
                venueBox,
                addAthleteBtn,
                addVenueBtn
        }) {

            c.setMaximumSize(size);
        }

        rightPanel.add(createBtn);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(updateBtn);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(deleteBtn);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(refreshBtn);

        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(new JLabel("Select Athlete"));
        rightPanel.add(athleteBox);
        rightPanel.add(addAthleteBtn);

        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(new JLabel("Select Venue"));
        rightPanel.add(venueBox);
        rightPanel.add(addVenueBtn);

        // CREATE
        createBtn.addActionListener(e -> {

            try {

                Match match =
                        new Match(
                                nameField.getText().trim(),
                                new ScheduledDate(
                                        LocalDate.parse(
                                                dateField.getText().trim()
                                        )
                                )
                        );

                DataStore.matches.add(match);

                refreshData();

                JOptionPane.showMessageDialog(
                        this,
                        "Match created!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {

            Match selected =
                    list.getSelectedValue();

            if (selected == null) {
                return;
            }

            try {

                selected.setName(
                        nameField.getText().trim()
                );

                selected.setScheduledDate(
                        new ScheduledDate(
                                LocalDate.parse(
                                        dateField.getText().trim()
                                )
                        )
                );

                refreshData();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {

            Match selected =
                    list.getSelectedValue();

            if (selected == null) {
                return;
            }

            DataStore.matches.remove(selected);

            refreshData();
        });

        // ADD ATHLETE
        addAthleteBtn.addActionListener(e -> {

            Match match =
                    list.getSelectedValue();

            Athlete athlete =
                    (Athlete) athleteBox.getSelectedItem();

            if (match == null || athlete == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select match and athlete!"
                );

                return;
            }

            try {

                match.addAthleteToMatch(athlete);

                refreshData();

                JOptionPane.showMessageDialog(
                        this,
                        "Athlete added!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // ADD VENUE
        addVenueBtn.addActionListener(e -> {

            Match match =
                    list.getSelectedValue();

            Venue venue =
                    (Venue) venueBox.getSelectedItem();

            if (match == null || venue == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select match and venue!"
                );

                return;
            }

            try {

                venue.addMatch(match);

                refreshData();

                JOptionPane.showMessageDialog(
                        this,
                        "Venue assigned!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // REFRESH
        refreshBtn.addActionListener(e -> {
            refreshData();
        });

        // FILL FORM
        list.addListSelectionListener(e -> {

            Match selected =
                    list.getSelectedValue();

            if (selected != null) {

                nameField.setText(
                        selected.getName()
                );

                dateField.setText(
                        selected.getScheduledDate()
                                .getScheduledDate()
                                .toString()
                );
            }
        });

        refreshData();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private void refreshData() {

        model.clear();

        for (Match m : DataStore.matches) {
            model.addElement(m);
        }

        athleteBox.removeAllItems();

        for (Athlete a : DataStore.athletes) {
            athleteBox.addItem(a);
        }

        venueBox.removeAllItems();

        for (Venue v : DataStore.venues) {
            venueBox.addItem(v);
        }

        repaint();
        revalidate();
    }
}