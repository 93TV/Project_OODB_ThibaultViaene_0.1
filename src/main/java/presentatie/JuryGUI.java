package presentatie;

import data.DataLaag;
import logica.Functie;
import logica.Official;
import logica.Wedstrijd;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JuryGUI {
    public JPanel manPanelJury;
    private JLabel wedstrijdLabelTitel;
    private JLabel labelOfficialTitel;
    private JTextField textFieldOfficial;
    private JComboBox comboBoxFuncties;
    private JLabel labelFunctieTitel;
    private JButton buttonJuryToevoegen;
    private JButton buttonTerug;
    private JButton verwijderButton;
    private JLabel labelJuryTitel;
    private JList listJury;
    private JComboBox comboBoxStrijden;
    private JTextField textFieldWedstrijd;
    private JLabel labelErrorJury;

    private DefaultListModel<Official> juryListModel;

    private void comboVuller() throws SQLException {
        DataLaag dl = new DataLaag();
        for (Wedstrijd wed : dl.geefWedstrijdEnID()) {
            comboBoxStrijden.addItem(wed);
        }
        for (Functie f : Functie.values()) {
            comboBoxFuncties.addItem(f);
        }
    }

    private void geefJury(int wedId) throws SQLException {
        DataLaag dl = new DataLaag();
        juryListModel.clear();
        for (Official o : dl.geefJuryPerWedstrijd(wedId)) {
            juryListModel.addElement(o);
        }
    }

    private Official maakOfficial() {
        return new Official(Integer.parseInt(textFieldOfficial.getText()), comboBoxFuncties.getSelectedItem().toString());
    }

    public JuryGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller();
        juryListModel = new DefaultListModel<>();
        listJury.setModel(juryListModel);


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

        comboBoxStrijden.addActionListener(e -> {
            try {
                System.out.println(comboBoxStrijden.getSelectedIndex());
                geefJury(comboBoxStrijden.getSelectedIndex() + 1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        verwijderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dl.deleteJury(maakOfficial(), Integer.parseInt(textFieldWedstrijd.getText()));
                    labelErrorJury.setText("Jury lid verwijderd!");
                    geefJury(Integer.parseInt(textFieldWedstrijd.getText()));
                } catch (IllegalArgumentException ex) {
                    labelErrorJury.setText(ex.getMessage());
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        buttonJuryToevoegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    try {
                        dl.insertJury(maakOfficial(), Integer.parseInt(textFieldWedstrijd.getText()));
                        labelErrorJury.setText("Jury toegevoegd!");
                        geefJury(Integer.parseInt(textFieldWedstrijd.getText()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IllegalArgumentException ex) {
                    labelErrorJury.setText(ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("JuryGUI");
        frame.setContentPane(new JuryGUI(frame).manPanelJury);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Jury Toevoegen");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


