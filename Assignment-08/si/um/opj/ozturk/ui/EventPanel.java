package si.um.opj.ozturk.ui;

import sport.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.time.LocalDate;

public class EventPanel extends JPanel {

    private DefaultListModel<Event> model = new DefaultListModel<>();
    private JList<Event> list = new JList<>(model);

    private JComboBox<Match> matchBox = new JComboBox<>();

    public EventPanel() {

        setLayout(new BorderLayout(10, 10));

        // FORM
        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();

        form.setBorder(BorderFactory.createTitledBorder("Create Event"));

        form.add(new JLabel("Event Name"));
        form.add(nameField);

        form.add(new JLabel("Date (YYYY-MM-DD)"));
        form.add(dateField);

        // LIST
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Events"));

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addBtn = new JButton("Add Event");
        JButton deleteBtn = new JButton("Delete Event");
        JButton updateBtn = new JButton("Update Event");
        JButton refreshBtn = new JButton("Refresh");

        JButton addMatchBtn = new JButton("Add Match → Event");

        Dimension size = new Dimension(200, 30);

        for (JComponent c : new JComponent[]{
                addBtn, deleteBtn, updateBtn, refreshBtn,
                matchBox, addMatchBtn
        }) {
            c.setMaximumSize(size);
        }

        rightPanel.add(addBtn);
        rightPanel.add(Box.createVerticalStrut(5));

        rightPanel.add(deleteBtn);
        rightPanel.add(Box.createVerticalStrut(5));

        rightPanel.add(updateBtn);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(refreshBtn);

        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(new JLabel("Select Match"));
        rightPanel.add(matchBox);
        rightPanel.add(addMatchBtn);

        // ADD EVENT
        addBtn.addActionListener(e -> {

            try {

                if (nameField.getText().trim().isEmpty()
                        || dateField.getText().trim().isEmpty()) {

                    throw new IllegalArgumentException(
                            "Fields cannot be empty!"
                    );
                }

                Event event = new Event(
                        nameField.getText().trim(),
                        new ScheduledDate(
                                LocalDate.parse(
                                        dateField.getText().trim()
                                )
                        )
                );

                DataStore.events.add(event);
                model.addElement(event);

                System.out.println(
                        "[SUCCESS] Event added."
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Event added!"
                );

                refreshMatches();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid input!"
                );
            }
        });

        // DELETE EVENT
        deleteBtn.addActionListener(e -> {

            Event selected =
                    list.getSelectedValue();

            if (selected == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select event!"
                );

                return;
            }

            DataStore.events.remove(selected);
            model.removeElement(selected);

            System.out.println(
                    "[SUCCESS] Event deleted."
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Deleted!"
            );
        });

        // UPDATE EVENT (NO NEW OBJECT CREATION)
        updateBtn.addActionListener(e -> {

            int index = list.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select event!"
                );

                return;
            }

            try {

                Event event =
                        DataStore.events.get(index);

                event.setName(
                        nameField.getText().trim()
                );

                event.setScheduledDate(
                        new ScheduledDate(
                                LocalDate.parse(
                                        dateField.getText().trim()
                                )
                        )
                );

                model.set(index, event);

                System.out.println(
                        "[SUCCESS] Event updated."
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Updated!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Update error!"
                );
            }
        });

        // REFRESH MATCHES
        refreshBtn.addActionListener(e -> refreshMatches());

        // ADD MATCH TO EVENT
        addMatchBtn.addActionListener(e -> {

            Event event =
                    list.getSelectedValue();

            Match match =
                    (Match) matchBox.getSelectedItem();

            if (event == null || match == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select event & match!"
                );

                return;
            }

            try {

                event.addMatch(match);

                list.repaint();

                JOptionPane.showMessageDialog(
                        this,
                        "Match added to event!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });

        list.addListSelectionListener(e -> {
            Event selected = list.getSelectedValue();

            if (selected != null) {

                nameField.setText(selected.getName());

                dateField.setText(
                        selected.getScheduledDate()
                                .getScheduledDate()
                                .toString()
                );
            }
        });

        refreshMatches();

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private void refreshMatches() {

        matchBox.removeAllItems();

        for (Match m : DataStore.matches) {
            matchBox.addItem(m);
        }
    }
}