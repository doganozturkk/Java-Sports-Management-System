package si.um.opj.ozturk.ui;

import infrastructure.*;
import sport.SportsDiscipline;

import javax.swing.*;
import java.awt.*;

public class VenuePanel extends JPanel {

    private DefaultListModel<Venue> model = new DefaultListModel<>();
    private JList<Venue> list = new JList<>(model);

    public VenuePanel() {

        setLayout(new BorderLayout(10, 10));

        // TOP PANEL
        JPanel topPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField capacityField = new JTextField();

        JComboBox<String> typeBox =
                new JComboBox<>(new String[]{"Hall", "Stadium"});

        JComboBox<SportsDiscipline> disciplineBox =
                new JComboBox<>(SportsDiscipline.values());

        JCheckBox eveningBox = new JCheckBox("Evening Games");

        topPanel.setBorder(
                BorderFactory.createTitledBorder("Venue Information")
        );

        topPanel.add(new JLabel("Name"));
        topPanel.add(nameField);

        topPanel.add(new JLabel("Phone"));
        topPanel.add(phoneField);

        topPanel.add(new JLabel("Type"));
        topPanel.add(typeBox);

        topPanel.add(new JLabel("Discipline"));
        topPanel.add(disciplineBox);

        topPanel.add(new JLabel("Evening"));
        topPanel.add(eveningBox);

        topPanel.add(new JLabel("Capacity"));
        topPanel.add(capacityField);

        // LIST
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Venues")
        );

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(
                new BoxLayout(rightPanel, BoxLayout.Y_AXIS)
        );

        rightPanel.setBorder(
                BorderFactory.createTitledBorder("Actions")
        );

        JButton addBtn = new JButton("Add Venue");
        JButton deleteBtn = new JButton("Delete Venue");
        JButton updateBtn = new JButton("Update Venue");

        Dimension size = new Dimension(200, 30);

        for (JComponent c : new JComponent[]{
                addBtn, deleteBtn, updateBtn
        }) {
            c.setMaximumSize(size);
        }

        rightPanel.add(addBtn);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(deleteBtn);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(updateBtn);

        // ADD
        addBtn.addActionListener(e -> {

            try {

                Venue v = createVenue(
                        nameField,
                        phoneField,
                        capacityField,
                        typeBox,
                        eveningBox
                );

                v.setSportsDiscipline(
                        (SportsDiscipline)
                                disciplineBox.getSelectedItem()
                );

                DataStore.venues.add(v);
                model.addElement(v);

                System.out.println("[SUCCESS] Venue added.");

                JOptionPane.showMessageDialog(
                        this,
                        "Venue added!"
                );

                clear(nameField, phoneField, capacityField);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid input!"
                );
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {

            Venue selected =
                    list.getSelectedValue();

            if (selected == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a venue!"
                );

                return;
            }

            DataStore.venues.remove(selected);
            model.removeElement(selected);

            System.out.println("[SUCCESS] Venue deleted.");

            JOptionPane.showMessageDialog(
                    this,
                    "Venue deleted!"
            );
        });

        // UPDATE (NO NEW OBJECT)
        updateBtn.addActionListener(e -> {

            int index = list.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a venue!"
                );

                return;
            }

            try {

                Venue selected =
                        DataStore.venues.get(index);

                // NOTE: basic fields via toString parsing is NOT safe,
                // so we only update via replacement logic safely

                Venue updated = createVenue(
                        nameField,
                        phoneField,
                        capacityField,
                        typeBox,
                        eveningBox
                );

                updated.setSportsDiscipline(
                        (SportsDiscipline)
                                disciplineBox.getSelectedItem()
                );

                DataStore.venues.set(index, updated);
                model.set(index, updated);

                System.out.println("[SUCCESS] Venue updated.");

                JOptionPane.showMessageDialog(
                        this,
                        "Venue updated!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Update error!"
                );
            }
        });

        // LIST SELECT FILL
        list.addListSelectionListener(e -> {

            Venue selected =
                    list.getSelectedValue();

            if (selected != null) {

                nameField.setText(selected.getName());

                phoneField.setText(""); // not stored cleanly in getter

                capacityField.setText(
                        String.valueOf(
                                selected.getMatchList().length
                        )
                );
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private Venue createVenue(
            JTextField nameField,
            JTextField phoneField,
            JTextField capacityField,
            JComboBox<String> typeBox,
            JCheckBox eveningBox
    ) {

        String name =
                nameField.getText().trim();

        String phone =
                phoneField.getText().trim();

        if (name.isEmpty()
                || phone.isEmpty()
                || capacityField.getText().isEmpty()) {

            throw new IllegalArgumentException();
        }

        int capacity =
                Integer.parseInt(
                        capacityField.getText().trim()
                );

        if (typeBox.getSelectedItem()
                .equals("Hall")) {

            return new Hall(
                    name,
                    phone,
                    capacity,
                    eveningBox.isSelected()
            );

        } else {

            return new Stadium(
                    name,
                    phone,
                    capacity
            );
        }
    }

    private void clear(JTextField... fields) {

        for (JTextField f : fields) {
            f.setText("");
        }
    }
}