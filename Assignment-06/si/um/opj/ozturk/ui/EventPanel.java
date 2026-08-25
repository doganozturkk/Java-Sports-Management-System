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

        setLayout(new BorderLayout(10,10));

        // FORM
        JPanel form = new JPanel(new GridLayout(2,2,5,5));

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

        Dimension size = new Dimension(200,30);

        addBtn.setMaximumSize(size);
        deleteBtn.setMaximumSize(size);
        updateBtn.setMaximumSize(size);
        refreshBtn.setMaximumSize(size);
        matchBox.setMaximumSize(size);
        addMatchBtn.setMaximumSize(size);

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
                Event ev = new Event(
                        nameField.getText().trim(),
                        new ScheduledDate(LocalDate.parse(dateField.getText().trim()))
                );

                DataStore.events.add(ev);
                model.addElement(ev);

                JOptionPane.showMessageDialog(this, "Event added!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            Event selected = list.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select event!");
                return;
            }

            DataStore.events.remove(selected);
            model.removeElement(selected);

            JOptionPane.showMessageDialog(this, "Deleted!");
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            Event selected = list.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select event!");
                return;
            }

            try {
                Event updated = new Event(
                        nameField.getText().trim(),
                        new ScheduledDate(LocalDate.parse(dateField.getText().trim()))
                );

                int index = list.getSelectedIndex();

                DataStore.events.set(index, updated);
                model.set(index, updated);

                JOptionPane.showMessageDialog(this, "Updated!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Update error!");
            }
        });

        // REFRESH MATCHES
        refreshBtn.addActionListener(e -> refreshMatches());

        // ADD MATCH TO EVENT
        addMatchBtn.addActionListener(e -> {
            Event ev = list.getSelectedValue();
            Match m = (Match) matchBox.getSelectedItem();

            if (ev == null || m == null) {
                JOptionPane.showMessageDialog(this, "Select event & match!");
                return;
            }

            try {
                ev.addMatch(m);
                list.repaint();
                JOptionPane.showMessageDialog(this, "Match added to event!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
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