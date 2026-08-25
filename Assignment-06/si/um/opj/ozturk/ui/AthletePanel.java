package si.um.opj.ozturk.ui;

import sport.Athlete;
import sport.SportsDiscipline;
import si.um.opj.ozturk.listeners.AddAthleteListener;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AthletePanel extends JPanel {

    private DefaultListModel<Athlete> model = new DefaultListModel<>();
    private JList<Athlete> list = new JList<>(model);

    private JTextField nameField = new JTextField();
    private JTextField surnameField = new JTextField();
    private JTextField numberField = new JTextField();
    private JTextField dateField = new JTextField();

    private JComboBox<SportsDiscipline> disciplineBox =
            new JComboBox<>(SportsDiscipline.values());

    public AthletePanel() {

        setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel(new GridLayout(5,2,5,5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Athlete Information"));

        topPanel.add(new JLabel("Name"));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Surname"));
        topPanel.add(surnameField);
        topPanel.add(new JLabel("Number"));
        topPanel.add(numberField);
        topPanel.add(new JLabel("Birth Date (YYYY-MM-DD)"));
        topPanel.add(dateField);
        topPanel.add(new JLabel("Discipline"));
        topPanel.add(disciplineBox);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Athletes"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addBtn = new JButton("Add Athlete");
        JButton updateBtn = new JButton("Update Athlete");
        JButton deleteBtn = new JButton("Delete Athlete");
        JButton refreshBtn = new JButton("Refresh");

        Dimension size = new Dimension(200, 30);

        for (JComponent c : new JComponent[]{
                addBtn, updateBtn, deleteBtn, refreshBtn
        }) {
            c.setMaximumSize(size);
        }

        rightPanel.add(addBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(updateBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(deleteBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(refreshBtn);

        // ADD
        addBtn.addActionListener(new AddAthleteListener(this));

        // UPDATE
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("[INFO] Update button clicked.");
                updateAthlete();
            }
        });

        // DELETE
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("[INFO] Delete button clicked.");
                deleteAthlete();
            }
        });

        // REFRESH
        refreshBtn.addActionListener(e -> {
            System.out.println("[INFO] Refresh button clicked.");
            refresh();
        });

        list.addListSelectionListener(e -> fillForm());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        refresh();
    }

    public void addAthlete() {
        try {
            if (nameField.getText().trim().isEmpty() ||
                    surnameField.getText().trim().isEmpty() ||
                    numberField.getText().trim().isEmpty() ||
                    dateField.getText().trim().isEmpty()) {

                throw new IllegalArgumentException("Fields cannot be empty!");
            }

            Athlete a = new Athlete(
                    nameField.getText().trim(),
                    surnameField.getText().trim(),
                    Integer.parseInt(numberField.getText().trim()),
                    LocalDate.parse(dateField.getText().trim())
            );

            a.setSportsDiscipline((SportsDiscipline) disciplineBox.getSelectedItem());

            DataStore.athletes.add(a);

            System.out.println("[SUCCESS] Athlete added: " + a.getName() + " " + a.getSurname());

            JOptionPane.showMessageDialog(this, "Athlete added!");
            refresh();
            clear();

        } catch (Exception ex) {
            System.out.println("[ERROR] Failed to add athlete.");
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void updateAthlete() {
        int index = list.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select an athlete!");
            return;
        }

        try {
            Athlete athlete = DataStore.athletes.get(index);

            athlete.setName(nameField.getText().trim());
            athlete.setSurname(surnameField.getText().trim());
            athlete.setAthleteNumber(
                    Integer.parseInt(numberField.getText().trim())
            );
            athlete.setBirthDate(
                    LocalDate.parse(dateField.getText().trim())
            );

            athlete.setSportsDiscipline(
                    (SportsDiscipline) disciplineBox.getSelectedItem()
            );

            System.out.println("[SUCCESS] Athlete updated.");

            JOptionPane.showMessageDialog(this, "Athlete updated!");
            refresh();

        } catch (Exception ex) {
            System.out.println("[ERROR] Update failed.");
            JOptionPane.showMessageDialog(this, "Update error!");
        }
    }

    private void deleteAthlete() {
        Athlete selected = list.getSelectedValue();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select an athlete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this athlete?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            DataStore.athletes.remove(selected);

            System.out.println("[SUCCESS] Athlete deleted.");

            JOptionPane.showMessageDialog(this, "Athlete deleted!");
            refresh();
        }
    }

    private void refresh() {
        model.clear();
        for (Athlete a : DataStore.athletes) {
            model.addElement(a);
        }
    }

    private void fillForm() {
        Athlete a = list.getSelectedValue();

        if (a != null) {
            nameField.setText(a.getName());
            surnameField.setText(a.getSurname());
            numberField.setText(String.valueOf(a.getAthleteNumber()));
            dateField.setText(a.getBirthDate().toString());
            disciplineBox.setSelectedItem(a.getSportsDiscipline());
        }
    }

    private void clear() {
        nameField.setText("");
        surnameField.setText("");
        numberField.setText("");
        dateField.setText("");
    }
}