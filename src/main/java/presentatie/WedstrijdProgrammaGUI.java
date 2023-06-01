package presentatie;

import data.DataLaag;
import logica.*;

import javax.swing.*;
import java.sql.SQLException;

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

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Wedstrijd wed : dl.geefWedstrijdEnID()) {
            comboBoxWedstrijd.addItem(wed.toString());
        }
        for (LeeftijdsCategorie lc : LeeftijdsCategorie.values()) {
            comboBoxLeeftijdsCategorie.addItem(lc.toString().replace("_", ""));
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
    }

    public WedstrijdProgrammaGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);
        buttonTerug.addActionListener(e -> {
            JFrame frame = new JFrame("mainGUI");
            frame.setContentPane(new MainGui(frame).mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
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
