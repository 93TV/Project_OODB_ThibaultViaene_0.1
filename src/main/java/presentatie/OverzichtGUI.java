package presentatie;

import data.DataLaag;
import logica.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class OverzichtGUI {
    public JPanel mainPanelOverzicht;
    private JComboBox comboBoxWedstrijden;
    private JList listZwemmers;
    private JList listJury;
    private JComboBox comboBoxWedstrijdProgrammas;
    private JButton buttonTerug;
    private JButton buttonSimulatie;
    private JList listProg;
    private JList listSerie;
    private JLabel labelSetZwembad;

    private DefaultListModel<WedstrijdProgramma> wedProgListModel;
    private DefaultListModel<Serie> serieListModel;
    private DefaultListModel<Deelname> deelnameDefaultListModel;
    private DefaultListModel<Official> officialDefaultListModel;
    private DefaultListModel<Besttijd> besttijdDefaultListModel;

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Wedstrijd wed : dl.geefWedstrijdNaamEnId()){
            comboBoxWedstrijden.addItem(wed.toString());
        }

    }

    private void geefWedstrijdProgrammas(DataLaag dl) throws SQLException {
        wedProgListModel.clear();
        for (WedstrijdProgramma wp : dl.geefWedstrijdProgrammas(comboBoxWedstrijden.getSelectedIndex()+1)){
            wedProgListModel.addElement(wp);
        }
    }

    public OverzichtGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);
        wedProgListModel = new DefaultListModel<>();
        serieListModel = new DefaultListModel<>();
        deelnameDefaultListModel = new DefaultListModel<>();
        officialDefaultListModel = new DefaultListModel<>();
        besttijdDefaultListModel = new DefaultListModel<>();
        listProg.setModel(wedProgListModel);
        listZwemmers.setModel(besttijdDefaultListModel);
        listJury.setModel(officialDefaultListModel);
        comboBoxWedstrijden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    geefWedstrijdProgrammas(dl);
                    listProg.setModel(wedProgListModel);
                    geefJury(dl, comboBoxWedstrijden.getSelectedIndex() + 1);
                    listJury.setModel(officialDefaultListModel);
                    labelSetZwembad.setText((dl.geefZwembadenNaamEnId(comboBoxWedstrijden.getSelectedIndex() +1)).toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonTerug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("mainGUI");
                frame.setContentPane(new MainGui(frame).mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Home");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                surroundingFrame.dispose();
            }
        });


        listProg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                WedstrijdProgramma wedProg = (WedstrijdProgramma) listProg.getModel().getElementAt(listProg.locationToIndex(e.getPoint()));
                try {
                    geefSeriesperWeds(dl, wedProg);
                    listSerie.setModel(serieListModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        listSerie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Serie s = (Serie) listSerie.getModel().getElementAt(listSerie.locationToIndex(e.getPoint()));
                try {
//                    geefDeelnames(dl, s);
                    geefBesttijden(dl, comboBoxWedstrijden.getSelectedIndex() + 1, s.getId());
                    listZwemmers.setModel(besttijdDefaultListModel);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonSimulatie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("SimulatieGUI");
                SimulatieGUI simGUI = null;
                try {
                    simGUI = new SimulatieGUI(frame);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    frame.setContentPane(new SimulatieGUI(frame).mainPanelSimulatie);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
                    simGUI.createUIComponents();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setTitle("Simulatie");

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                surroundingFrame.dispose();
            }
        });

    }

    private void geefSeriesperWeds(DataLaag dl, WedstrijdProgramma wedProg) throws SQLException {
        serieListModel.clear();
        for (Serie s: dl.geefSeries(wedProg.getId())){
            serieListModel.addElement(s);
        }
    }

    private void geefDeelnames(DataLaag dl, Serie s) throws SQLException {
        deelnameDefaultListModel.clear();
        for (Deelname d: dl.getDeelnamesSerie(s.getId())){
            deelnameDefaultListModel.addElement(d);
        }
    }

    private void geefBesttijden(DataLaag dl, int wedId, int serieId) throws SQLException {
        besttijdDefaultListModel.clear();
        for (Besttijd b : dl.getZwemmersEnBesttijden(wedId, serieId)){
            besttijdDefaultListModel.addElement(b);
        }
    }

    private void geefJury(DataLaag dl, int wedId) throws SQLException {
        officialDefaultListModel.clear();
        for (Official of : dl.geefJuryPerWedstrijd(wedId)){
            officialDefaultListModel.addElement(of);
        }
    }


    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("OverzichtGUI");
        frame.setContentPane(new OverzichtGUI(frame).mainPanelOverzicht);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Overzicht");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
