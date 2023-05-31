package presentatie;

import data.DataLaag;
import logica.Wedstrijd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JuryGUI {
    public JPanel manPanelJury;
    private JComboBox comboBoxWedstrijden;
    private JLabel wedstrijdLabelTitel;
    private JLabel labelOfficialTitel;
    private JTextField textFieldOfficial;
    private JComboBox comboBoxFuncties;
    private JLabel labelFunctieTitel;
    private JButton buttonJuryToevoegen;
    private JButton buttonTerug;
    private JButton verwijderButton;
    private JTextArea textArea1;
    private JLabel labelJuryTitel;

    private void comboVuller() throws SQLException {
        DataLaag dl = new DataLaag();
        for (Wedstrijd wed : dl.geefWedstrijdNaamEnId()){
            comboBoxWedstrijden.addItem(wed);
        }

    }

    public JuryGUI(JFrame surroundingFrame) throws SQLException {
        comboVuller();
        buttonJuryToevoegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
        JFrame frame = new JFrame("JuryGUI");
        frame.setContentPane(new JuryGUI(frame).manPanelJury);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Jury Toevoegen");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


