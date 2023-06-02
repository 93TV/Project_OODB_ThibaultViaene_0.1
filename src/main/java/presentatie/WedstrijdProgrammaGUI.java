package presentatie;

import data.DataLaag;
import logica.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;

public class WedstrijdProgrammaGUI {


    public JPanel mainWedstrijdProgrammaPanel;
    private JComboBox comboBoxWedstrijd;
    private JButton buttonTerug;
    private JButton buttonProgrammaMaken;
    private JLabel labelWedstrijdTitel;
    private JLabel labelCategorieTitel;
    private JComboBox comboBoxLeeftijdsCategorie;
    private JLabel labelAanvangTitel;
    private JComboBox comboBoxUur;
    private JComboBox comboBoxMinuut;
    private JComboBox comboBoxSeconde;
    private JLabel labelSlagTitel;
    private JComboBox comboBoxSlag;
    private JLabel labelAfstandTitel;
    private JComboBox comboBoxAfstand;
    private JCheckBox checkBoxAflossing;
    private JComboBox comboBoxGeslacht;
    private JLabel labelGeslachtTitel;
    private JLabel labelErrorWedstrijdProgramma;
    private JButton buttonVolgende;

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Wedstrijd wed : dl.geefWedstrijdEnID()) {
            comboBoxWedstrijd.addItem(wed.toString());
        }
        for (LeeftijdsCategorie lc : LeeftijdsCategorie.values()) {
            comboBoxLeeftijdsCategorie.addItem(Helper.leeftijdsCategorie(lc));
        }
        for (int i = 1; i < 25; i++) {
            comboBoxUur.addItem(i);
        }
        for (int i = 0; i < 61; i++) {
            comboBoxMinuut.addItem(i);
        }
        for (int i = 0; i < 61; i++) {
            comboBoxSeconde.addItem(i);
        }
        for (Geslacht g : Geslacht.values()) {
            comboBoxGeslacht.addItem(g.toString());
        }
        for (Afstand a : Afstand.values()) {
            comboBoxAfstand.addItem(a.toString().replace("_", ""));
        }
        for (Slag sl : Slag.values()) {
            comboBoxSlag.addItem(sl.toString());
        }
    }

    private WedstrijdProgramma maakProgramma() {
        String timeString = comboBoxUur.getSelectedItem().toString() + ":" + comboBoxMinuut.getSelectedItem().toString() + ":" + comboBoxSeconde.getSelectedItem().toString();
        Time time = java.sql.Time.valueOf(timeString);
        return new WedstrijdProgramma(Helper.reverseLeeftijd(comboBoxLeeftijdsCategorie.getSelectedItem().toString()), time, comboBoxWedstrijd.getSelectedIndex() + 1, Slag.valueOf(comboBoxSlag.getSelectedItem().toString()),
                Afstand.valueOf(comboBoxAfstand.getSelectedItem().toString().replaceFirst("", "_")), checkBoxAflossing.isSelected(), Geslacht.valueOf(comboBoxGeslacht.getSelectedItem().toString()));

    }



    public WedstrijdProgrammaGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);
        buttonTerug.addActionListener(e -> {
            JFrame frame = new JFrame("mainGUI");
            frame.setContentPane(new MainGui(frame).mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
        });
        buttonProgrammaMaken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dl.insertProgramma(maakProgramma());
                    dl.insertProgrammaWedstrijd(maakProgramma());
                    labelErrorWedstrijdProgramma.setText("Wedstrijdprogramma aangemaakt!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalArgumentException ex) {
                    labelErrorWedstrijdProgramma.setText(ex.getMessage());
                }
            }
        });
        buttonVolgende.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("SerieAanmakenGUI");
                try {
                    frame.setContentPane(new SerieAanmakenGUI(frame).mainPanelSerieAanmaken);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                surroundingFrame.dispose();
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("WedstrijdProgrammaGUI");
        frame.setContentPane(new WedstrijdProgrammaGUI(frame).mainWedstrijdProgrammaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wedstrijdprogramma Toevoegen");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
