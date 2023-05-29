package presentatie;

import logica.AantalBanen;
import logica.Adres;
import logica.Lengte;
import logica.Zwembad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZwembadGUI {
    private JLabel labelTitel;
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
    private JPanel mainPanel;
    private JLabel labelErrorZwembad;

    private void ComboVuller() {
        for (Lengte lengte : Lengte.values()) {
            comboBoxLengte.addItem(lengte.toString().replace("_", ""));
        }
        for (AantalBanen banen : AantalBanen.values()) {
            comboBoxAantalBanen.addItem(banen.toString().replace("_", ""));
        }
    }

    public ZwembadGUI() {
        ComboVuller();
        buttonMaakZwembad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Adres adres = new Adres(textFieldStraat.getText(), textFieldHuisnr.getText(), textFieldGemeente.getText(), Integer.parseInt(textFieldPostcode.getText()));
                    Zwembad zwembad = new Zwembad(adres, textFieldNaam.getText(), Lengte.valueOf(comboBoxLengte.getSelectedItem().toString().replaceFirst("", "_")), AantalBanen.valueOf(comboBoxAantalBanen.getSelectedItem().toString().replaceFirst("", "_")));
                    labelErrorZwembad.setText(zwembad.toString());
                } catch (IllegalArgumentException ex) {
                    labelErrorZwembad.setText(ex.getMessage());
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ZwembadGUI");
        frame.setContentPane(new ZwembadGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
