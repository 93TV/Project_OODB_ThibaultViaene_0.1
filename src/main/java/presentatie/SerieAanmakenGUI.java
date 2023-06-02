package presentatie;

import data.DataLaag;
import logica.Serie;
import logica.WedstrijdProgramma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;

public class SerieAanmakenGUI {
    public  JPanel mainPanelSerieAanmaken;
    private JButton buttonTerug;
    private JButton buttonSerieAanmaken;
    private JLabel labelWPTitel;
    private JComboBox comboBoxWedProgram;
    private JComboBox comboBoxSeconden;
    private JComboBox comboBoxUur;
    private JComboBox comboBoxMinuut;
    private JButton buttonZwemmersToevoegen;
    private JLabel labelErrorSerie;

    private void comboVuller(DataLaag dl) throws SQLException {
        for(WedstrijdProgramma wp : dl.geefWedstrijdProgrammas()){
            comboBoxWedProgram.addItem(wp.toString());
        }
        for (int i = 1; i < 25; i++) {
            comboBoxUur.addItem(i);
        }
        for (int i = 0; i < 61; i++) {
            comboBoxMinuut.addItem(i);
        }
        for (int i = 0; i < 61; i++) {
            comboBoxSeconden.addItem(i);
        }
    }

    public SerieAanmakenGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);
        buttonSerieAanmaken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dl.insertSessie(maakSerie());
                    labelErrorSerie.setText("Serie aangemaakt!");
                } catch (SQLException ex) {
                    labelErrorSerie.setText(ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonTerug.addActionListener(e -> {
            JFrame frame = new JFrame("mainGUI");
            frame.setContentPane(new MainGui(frame).mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
        });
        buttonZwemmersToevoegen.addActionListener(e -> {
            JFrame frame = new JFrame("ZwemmersToevoegen");
            try {
                frame.setContentPane(new ZwemmersToevoegenGUI(frame).mainPanelZwemmersToevoegen);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
        });
    }

    private Serie maakSerie() {
        String uur = comboBoxUur.getSelectedItem().toString();
        String min = comboBoxMinuut.getSelectedItem().toString();
        String sec = comboBoxSeconden.getSelectedItem().toString();
        String timeString = uur + ":" + min + ":" + sec;
        Time sqlTime = java.sql.Time.valueOf(timeString);
        return new Serie(comboBoxWedProgram.getSelectedIndex() + 1, sqlTime);
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("SerieAanmakenGUI");
        frame.setContentPane(new SerieAanmakenGUI(frame).mainPanelSerieAanmaken);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Serie Aanmaken");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
