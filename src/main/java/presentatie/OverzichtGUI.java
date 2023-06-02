package presentatie;

import data.DataLaag;
import logica.Wedstrijd;
import logica.WedstrijdProgramma;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class OverzichtGUI {
    public JPanel mainPanelOverzicht;
    private JComboBox comboBoxWedstrijden;
    private JList listZwemmers;
    private JList listJury;
    private JLabel labelSetZwembad;
    private JComboBox comboBoxWedstrijdProgrammas;
    private JButton buttonTerug;
    private JButton buttonSimulatie;
    private JList listProg;
    private JList listSerie;

    private DefaultListModel<WedstrijdProgramma> wedProgListModel;
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
        listProg.setModel(wedProgListModel);
        comboBoxWedstrijden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    geefWedstrijdProgrammas(dl);
                    listProg.setModel(wedProgListModel);
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
                System.out.println(wedProg);
            }
        });
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
