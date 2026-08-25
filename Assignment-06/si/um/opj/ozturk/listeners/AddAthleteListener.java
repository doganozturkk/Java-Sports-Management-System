package si.um.opj.ozturk.listeners;

import si.um.opj.ozturk.ui.AthletePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAthleteListener implements ActionListener {

    private AthletePanel panel;

    public AddAthleteListener(AthletePanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("[INFO] AddAthleteListener triggered successfully.");
        panel.addAthlete();
    }
}