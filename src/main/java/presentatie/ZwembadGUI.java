package presentatie;

import data.DataLaag;
import logica.AantalBanen;
import logica.Adres;
import logica.Lengte;
import logica.Zwembad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ZwembadGUI {
    private JLabel labelAdresTitel;
    private JTextField textFieldStraat;
    private JTextField textFieldGemeente;
    private JTextField textFieldPostcode;
    private JLabel labelStraatTitel;
    private JTextField textFieldHuisnr;
    private JLabel labelHuisnummerTitel;
    private JLabel labelGemeenteTitel;
    private JLabel labelPostcodeTitel;
    private JButton buttonMaakZwembad;
    private JComboBox comboBoxAantalBanen;
    private JComboBox comboBoxLengte;
    private JTextField textFieldNaam;
    private JLabel labelZwembadTitel;
    private JLabel labelNaamTitel;
    private JLabel labelLengteTitel;
    private JLabel labelAantalBanenTitel;
    public JPanel mainPanelZwb;
    private JLabel labelErrorZwembad;
    private JTextArea textAreaZwembadOverzicht;
    private JLabel labelZwembadenOverzicht;
    private JButton buttonTerug;
    private JButton buttonNaarWedstrijd;

    private void ComboVuller() {
        for (Lengte lengte : Lengte.values()) {
            comboBoxLengte.addItem(lengte.toString().replace("_", ""));
        }
        for (AantalBanen banen : AantalBanen.values()) {
            comboBoxAantalBanen.addItem(banen.toString().replace("_", ""));
        }
    }



    private Adres maakAdres() {
        if (textFieldStraat.getText().isEmpty()) throw new IllegalArgumentException("Vul een straat in.");
        if (textFieldHuisnr.getText().isEmpty()) throw new IllegalArgumentException("Vul een huisnummer in.");
        if (textFieldGemeente.getText().isEmpty()) throw new IllegalArgumentException("Vul een gemeente in.");
        String postcodeText = textFieldPostcode.getText();
        if (postcodeText.isEmpty()) {
            throw new IllegalArgumentException("Vul een Postcode in.");
        }
        try {
            int postcode = Integer.parseInt(postcodeText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Postcode moet een geheel getal zijn.");
        }
        return new Adres(textFieldStraat.getText(), textFieldHuisnr.getText(), textFieldGemeente.getText(), Integer.parseInt(textFieldPostcode.getText()));
    }

    private Zwembad maakZwembad() {
        if (textFieldNaam.getText().isEmpty())
            throw new IllegalArgumentException("Gelieve een zwembad naam in te geven");
        return new Zwembad(maakAdres(), textFieldNaam.getText(), Lengte.valueOf(comboBoxLengte.getSelectedItem().toString().replaceFirst("", "_")), AantalBanen.valueOf(comboBoxAantalBanen.getSelectedItem().toString().replaceFirst("", "_")));
    }

    private void vulTextField(DataLaag dl) throws SQLException {
        for (Zwembad zwb : dl.geefZwembadenNaamEnId()){
            textAreaZwembadOverzicht.append(zwb + "\n");
        }
    }

    public ZwembadGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        ComboVuller();
        vulTextField(dl);
        buttonMaakZwembad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textAreaZwembadOverzicht.setText("");
                    dl.insertAdres(maakAdres());
                    dl.insertZwembad(maakZwembad(), dl.checkAdres(maakAdres()));
                    labelErrorZwembad.setForeground(Color.GREEN);
                    labelErrorZwembad.setText("Zwembad aangemaakt!");
                    vulTextField(dl);
                } catch (IllegalArgumentException ex) {
                    labelErrorZwembad.setForeground(Color.RED);
                    labelErrorZwembad.setText(ex.getMessage());
                } catch (SQLException ex) {
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


        buttonNaarWedstrijd.addActionListener(e -> {
            JFrame frame = new JFrame("WedstrijdGUI");
            try {
                frame.setContentPane(new WedstrijdGUI(frame).mainPanelWedstrijd);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Wedstrijd Aanmaken");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            surroundingFrame.dispose();
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("ZwembadGUI");
        frame.setContentPane(new ZwembadGUI(frame).mainPanelZwb);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zwembad aanmaken");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
