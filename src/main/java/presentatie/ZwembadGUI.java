package presentatie;

import data.DataLaag;
import logica.AantalBanen;
import logica.Adres;
import logica.Lengte;
import logica.Zwembad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private JPanel mainPanel;
    private JLabel labelErrorZwembad;
    private JTextArea textAreaZwembadOverzicht;
    private JLabel labelZwembadenOverzicht;

    private void ComboVuller() {
        for (Lengte lengte : Lengte.values()) {
            comboBoxLengte.addItem(lengte.toString().replace("_", ""));
        }
        for (AantalBanen banen : AantalBanen.values()) {
            comboBoxAantalBanen.addItem(banen.toString().replace("_", ""));
        }
    }

    private void zwembadLijstWeergave (ArrayList<Zwembad> zwembaden) {
        String list = "";
        for (Zwembad zwb : zwembaden) {
            list += zwb + "\n";
        }
        textAreaZwembadOverzicht.setText(list);
    }

    private Adres maakAdres () {
        return new Adres(textFieldStraat.getText(), textFieldHuisnr.getText(), textFieldGemeente.getText(), Integer.parseInt(textFieldPostcode.getText()));
    }

    private Zwembad maakZwembad () {
        return new Zwembad(maakAdres(), textFieldNaam.getText(), Lengte.valueOf(comboBoxLengte.getSelectedItem().toString().replaceFirst("", "_")), AantalBanen.valueOf(comboBoxAantalBanen.getSelectedItem().toString().replaceFirst("", "_")));
    }
    public ZwembadGUI() {
        ComboVuller();
        buttonMaakZwembad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataLaag dl = new DataLaag();
                    dl.maakAdresAan(maakAdres());
                    dl.maakZwembadAan(maakZwembad());
                    labelErrorZwembad.setText("Zwembad aangemaakt!");
                    ArrayList<Zwembad> zwembadenList = dl.geefZwembadenMetAdressen();
                    zwembadLijstWeergave(zwembadenList);
                } catch (IllegalArgumentException ex) {
                    labelErrorZwembad.setText(ex.getMessage());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ZwembadGUI");
        frame.setContentPane(new ZwembadGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zwembad aanmaken");
        frame.pack();
        frame.setVisible(true);
    }

}
