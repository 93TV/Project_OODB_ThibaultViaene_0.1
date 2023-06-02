package presentatie;

import data.DataLaag;
import logica.Deelname;
import logica.Serie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ZwemmersToevoegenGUI {
    public JPanel mainPanelZwemmersToevoegen;
    private JList listSerie;
    private JComboBox comboBoxSeries;
    private JTextField textFieldVoorNaam;
    private JTextField textFieldAchterNaam;
    private JButton buttonZwemmerToevoegen;
    private JButton buttonTerug;
    private JLabel labelErrorZwemmers;
    private DefaultListModel<Deelname> serieListModel ;

    public ZwemmersToevoegenGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);
        serieListModel = new DefaultListModel<>();
        listSerie.setModel(serieListModel);
        buttonTerug.addActionListener(e -> {
            JFrame frame = new JFrame("mainGUI");
            frame.setContentPane(new MainGui(frame).mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            surroundingFrame.dispose();
        });
        buttonZwemmerToevoegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSerie.setModel(serieListModel);
                try {
                    if (textFieldVoorNaam.getText().isEmpty() || textFieldAchterNaam.getText().isEmpty()) throw new IllegalArgumentException("vul naam in!");
                    dl.insertDeelname(deelname(textFieldAchterNaam.getText(), textFieldVoorNaam.getText(), dl));
                    labelErrorZwemmers.setText("Zwemmer Toegevoegd");
                    listVuller(dl, comboBoxSeries.getSelectedIndex() + 1);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalArgumentException ex) {
                    labelErrorZwemmers.setText(ex.getMessage());
                    listSerie.setModel(serieListModel);
                }
            }
        });
        comboBoxSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listVuller(dl, comboBoxSeries.getSelectedIndex() + 1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void listVuller(DataLaag dl, int serieId) throws SQLException {
        serieListModel.clear();
        for (Deelname dn : dl.getDeelnamesSerie(serieId)) {
            serieListModel.addElement(dn);
        }
    }
    private Deelname deelname(String naam, String voorNaam, DataLaag dl) throws SQLException {
        int serieId = comboBoxSeries.getSelectedIndex() + 1;
        Deelname dn;
        if ( dl.getEersteVrijeBaan(serieId) != -1) {
            if (dl.getZwemmerId(naam, voorNaam) != -1) dn = new Deelname(dl.getZwemmerId(naam, voorNaam), serieId, dl.getEersteVrijeBaan(serieId));
            else throw new IllegalArgumentException("Zwemmer met deze naam bestaat niet!");
        } else throw new IllegalArgumentException("Geen banen meer vrij!");
        return dn;
    }

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Serie s : dl.geefSeries()){
            comboBoxSeries.addItem(s.toString());
        }
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("ZwemmersToevoegen");
        frame.setContentPane(new ZwemmersToevoegenGUI(frame).mainPanelZwemmersToevoegen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
