package presentatie;

import data.DataLaag;
import logica.DagDeel;
import logica.TijdsRegistratie;
import logica.Wedstrijd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class WedstrijdGUI {
    public JPanel mainPanelWedstrijd;
    private JTextField textFieldNaamWedstrijd;
    private JComboBox comboBoxTijdregistratie;
    private JComboBox comboBoxDagDeel;
    private JButton ButtonWedstrijdAanmaken;
    private JLabel labelErrorWedstrijd;
    private JLabel labelZwembadTitel;
    private JLabel labelNaamTitel;
    private JLabel labelTijdTitel;
    private JLabel labelDagdeelTitel;
    private JButton buttonTerug;
    private JComboBox comboBoxDag;
    private JComboBox comboBoxMaand;
    private JComboBox comboBoxJaar;
    private JLabel labelDagTitel;
    private JComboBox comboBoxZwembaden;

    private void comboVuller(){

        for (TijdsRegistratie tr : TijdsRegistratie.values()){
            comboBoxTijdregistratie.addItem(tr.toString());
        }
        for (DagDeel dd : DagDeel.values()){
            comboBoxDagDeel.addItem(dd.toString());
        }
        for (int i = 1; i < 32; i++){
            comboBoxDag.addItem(i);
        }
        for (int i = 1; i < 13; i++){
            comboBoxMaand.addItem(i);
        }
        for (int i = 2023; i < 2050; i++){
            comboBoxJaar.addItem(i);
        }
    }

    private Wedstrijd maakWedstrijd(){
        if (textFieldNaamWedstrijd.getText().isEmpty()) throw new IllegalArgumentException("Gelieve een naam voor de wedstrijd in te geven!");
        String dag = comboBoxDag.getSelectedItem().toString();
        String maand = comboBoxMaand.getSelectedItem().toString();
        String jaar = comboBoxJaar.getSelectedItem().toString();
        String datum = jaar + "-" + maand + "-" + dag;
        Date sqlDatum = Date.valueOf(datum);
        return new Wedstrijd(comboBoxZwembaden.getSelectedIndex(),textFieldNaamWedstrijd.getText(), sqlDatum, TijdsRegistratie.valueOf(comboBoxTijdregistratie.getSelectedItem().toString()), DagDeel.valueOf(comboBoxDagDeel.getSelectedItem().toString()));
    }


    public WedstrijdGUI(JFrame surroundingFrame) {
        comboVuller();

        buttonTerug.addActionListener(e -> {
            JFrame frame = new JFrame("mainGUI");
            frame.setContentPane(new mainGUI(frame).mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200,200);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            surroundingFrame.dispose();
        });
        ButtonWedstrijdAanmaken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataLaag dl = new DataLaag();
                    dl.insertWedstrijd(maakWedstrijd());
                    labelErrorWedstrijd.setText("Wedstrijd aangemaakt!");
                } catch (IllegalArgumentException ex) {
                    labelErrorWedstrijd.setText(ex.getMessage());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WedstrijdGUI");
        frame.setContentPane(new WedstrijdGUI(frame).mainPanelWedstrijd);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wedstrijd Aanmaken");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


