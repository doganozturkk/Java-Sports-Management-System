package si.um.opj.ozturk.ui;

import sport.Athlete;
import sport.SportsDiscipline;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.time.LocalDate;

public class AthletePanel extends JPanel {

    private DefaultListModel<Athlete> model = new DefaultListModel<>();
    private JList<Athlete> list = new JList<>(model);

    public AthletePanel() {

        setLayout(new BorderLayout(10,10));

        // TOP PANEL (FORM)
        JPanel topPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Athlete Information"));

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField numberField = new JTextField();
        JTextField dateField = new JTextField();

        JComboBox<SportsDiscipline> disciplineBox =
                new JComboBox<>(SportsDiscipline.values());

        topPanel.add(new JLabel("Name"));
        topPanel.add(nameField);

        topPanel.add(new JLabel("Surname"));
        topPanel.add(surnameField);

        topPanel.add(new JLabel("Athlete Number"));
        topPanel.add(numberField);

        topPanel.add(new JLabel("Birth Date (YYYY-MM-DD)"));
        topPanel.add(dateField);

        topPanel.add(new JLabel("Discipline"));
        topPanel.add(disciplineBox);

        // CENTER (LIST)
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Athletes"));

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addBtn = new JButton("Add Athlete");
        JButton deleteBtn = new JButton("Delete Athlete");
        JButton updateBtn = new JButton("Update Athlete");

        Dimension size = new Dimension(200, 30);

        addBtn.setMaximumSize(size);
        deleteBtn.setMaximumSize(size);
        updateBtn.setMaximumSize(size);

        rightPanel.add(addBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(deleteBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(updateBtn);

        // ADD
        addBtn.addActionListener(e -> {
            try {
                if (nameField.getText().trim().isEmpty() ||
                        surnameField.getText().trim().isEmpty() ||
                        numberField.getText().trim().isEmpty() ||
                        dateField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException();
                }

                Athlete a = new Athlete(
                        nameField.getText().trim(),
                        surnameField.getText().trim(),
                        Integer.parseInt(numberField.getText().trim()),
                        LocalDate.parse(dateField.getText().trim())
                );

                a.setSportsDiscipline((SportsDiscipline) disciplineBox.getSelectedItem());

                DataStore.athletes.add(a);
                model.addElement(a);

                JOptionPane.showMessageDialog(this, "Athlete added!");
                clearFields(nameField, surnameField, numberField, dateField);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            Athlete selected = list.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select an athlete!");
                return;
            }

            DataStore.athletes.remove(selected);
            model.removeElement(selected);

            JOptionPane.showMessageDialog(this, "Athlete deleted!");
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            Athlete selected = list.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select an athlete!");
                return;
            }

            try {
                Athlete updated = new Athlete(
                        nameField.getText().trim(),
                        surnameField.getText().trim(),
                        Integer.parseInt(numberField.getText().trim()),
                        LocalDate.parse(dateField.getText().trim())
                );

                updated.setSportsDiscipline((SportsDiscipline) disciplineBox.getSelectedItem());

                int index = list.getSelectedIndex();

                DataStore.athletes.set(index, updated);
                model.set(index, updated);

                JOptionPane.showMessageDialog(this, "Athlete updated!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Update error!");
            }
        });

        // LIST SELECTION FORM FILL
        list.addListSelectionListener(e -> {
            Athlete selected = list.getSelectedValue();

            if (selected != null) {
                nameField.setText(selected.getName());
                surnameField.setText(selected.getSurname());
                numberField.setText(String.valueOf(selected.getAthleteNumber()));
                dateField.setText(String.valueOf(selected.getBirthDate()));
                disciplineBox.setSelectedItem(selected.getSportsDiscipline());
            }
        });

        // LAYOUT
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField f : fields) f.setText("");
    }
}