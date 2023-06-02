package presentatie;

import data.DataLaag;
import logica.Functie;
import logica.Official;
import logica.Wedstrijd;

import javax.swing.*;
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
    private JComboBox comboBoxWedstrijden;
    private JLabel labelErrorJury;
    private JButton buttonNaarWedstrijdProgramma;

    private DefaultListModel<Official> juryListModel;

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Wedstrijd wed : dl.geefWedstrijdEnID()) {
            comboBoxWedstrijden.addItem(wed.toString());
        }
        for (Functie f : Functie.values()) {
            comboBoxFuncties.addItem(f);
        }
    }

    private void geefJury(DataLaag dl, int wedId) throws SQLException {
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
        comboVuller(dl);
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

        comboBoxWedstrijden.addActionListener(e -> {
            try {
                geefJury(dl, comboBoxWedstrijden.getSelectedIndex() + 1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        verwijderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textFieldOfficial.getText().isEmpty()) throw new IllegalArgumentException("Gelieve een id in te vullen!");
                    dl.deleteJury(maakOfficial(), comboBoxWedstrijden.getSelectedIndex() + 1);
                    labelErrorJury.setText("Jury lid verwijderd!");
                    geefJury(dl, comboBoxWedstrijden.getSelectedIndex() + 1);
                } catch (IllegalArgumentException ex) {
                    labelErrorJury.setText(ex.getMessage());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        buttonJuryToevoegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    try {
                        if (textFieldOfficial.getText().isEmpty()) throw new IllegalArgumentException("Gelieve een id in te vullen!");
                        dl.insertJury(maakOfficial(), comboBoxWedstrijden.getSelectedIndex() + 1);
                        labelErrorJury.setText("Jury toegevoegd!");
                        geefJury(dl, comboBoxWedstrijden.getSelectedIndex() + 1);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IllegalArgumentException ex) {
                    labelErrorJury.setText(ex.getMessage());
                }
            }
        });
        buttonNaarWedstrijdProgramma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("WedstrijdProgrammaGUI");
                try {
                    frame.setContentPane(new WedstrijdProgrammaGUI(frame).mainWedstrijdProgrammaPanel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Wedstrijdprogramma Toevoegen");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                surroundingFrame.dispose();
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


