package si.um.opj.ozturk.ui;

import infrastructure.Venue;
import sport.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MatchPanel extends JPanel {

    private DefaultListModel<Match> model = new DefaultListModel<>();
    private JList<Match> list = new JList<>(model);

    private JComboBox<Athlete> athleteBox = new JComboBox<>();
    private JComboBox<Venue> venueBox = new JComboBox<>();

    public MatchPanel() {

        setLayout(new BorderLayout(10, 10));

        // TOP PANEL
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();

        topPanel.setBorder(
                BorderFactory.createTitledBorder("Create / Update Match")
        );

        topPanel.add(new JLabel("Match Name"));
        topPanel.add(nameField);

        topPanel.add(new JLabel("Date (YYYY-MM-DD)"));
        topPanel.add(dateField);

        // CENTER LIST
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Matches")
        );

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(
                new BoxLayout(rightPanel, BoxLayout.Y_AXIS)
        );

        rightPanel.setBorder(
                BorderFactory.createTitledBorder("Actions")
        );

        JButton createBtn = new JButton("Create Match");
        JButton updateBtn = new JButton("Update Match");
        JButton deleteBtn = new JButton("Delete Match");
        JButton refreshBtn = new JButton("Refresh");

        JButton addAthleteBtn = new JButton("Add Athlete → Match");
        JButton addVenueBtn = new JButton("Add Match → Venue");

        Dimension size = new Dimension(200, 30);

        for (JComponent c : new JComponent[]{
                createBtn, updateBtn, deleteBtn, refreshBtn,
                athleteBox, venueBox,
                addAthleteBtn, addVenueBtn
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

        // CREATE MATCH
        createBtn.addActionListener(e -> {

            try {

                if (nameField.getText().trim().isEmpty()
                        || dateField.getText().trim().isEmpty()) {

                    throw new IllegalArgumentException(
                            "Fields cannot be empty!"
                    );
                }

                Match match = new Match(
                        nameField.getText().trim(),
                        new ScheduledDate(
                                LocalDate.parse(
                                        dateField.getText().trim()
                                )
                        )
                );

                DataStore.matches.add(match);
                model.addElement(match);

                System.out.println("[SUCCESS] Match created.");

                JOptionPane.showMessageDialog(
                        this,
                        "Match created!"
                );

                clear(nameField, dateField);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // UPDATE MATCH (NO NEW OBJECT)
        updateBtn.addActionListener(e -> {

            Match selected =
                    list.getSelectedValue();

            if (selected == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a match!"
                );

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

                model.set(
                        list.getSelectedIndex(),
                        selected
                );

                System.out.println("[SUCCESS] Match updated.");

                JOptionPane.showMessageDialog(
                        this,
                        "Match updated!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // DELETE MATCH
        deleteBtn.addActionListener(e -> {

            Match selected =
                    list.getSelectedValue();

            if (selected == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a match!"
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete this match?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirm == JOptionPane.YES_OPTION) {

                DataStore.matches.remove(selected);
                model.removeElement(selected);

                System.out.println("[SUCCESS] Match deleted.");

                JOptionPane.showMessageDialog(
                        this,
                        "Match deleted!"
                );
            }
        });

        // ADD ATHLETE TO MATCH
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

        // ADD VENUE TO MATCH
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

                match.setVenue(venue);
                venue.addMatch(match);

                JOptionPane.showMessageDialog(
                        this,
                        "Match added to venue!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        // FORM FILL
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

        athleteBox.removeAllItems();
        venueBox.removeAllItems();

        for (Athlete a : DataStore.athletes) {
            athleteBox.addItem(a);
        }

        for (Venue v : DataStore.venues) {
            venueBox.addItem(v);
        }
    }

    private void clear(JTextField... fields) {

        for (JTextField f : fields) {
            f.setText("");
        }
    }
}