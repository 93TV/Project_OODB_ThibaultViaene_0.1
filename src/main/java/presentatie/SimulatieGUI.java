package presentatie;

import data.DataLaag;
import logica.Serie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SimulatieGUI {
    public JPanel mainPanelSimulatie;
    private JPanel tekenPanel;
    private JComboBox comboBoxSerie;
    private JButton buttonSimulatie;
    private JButton buttonTerug;
    private JLabel labelWedTitel;
    private JLabel labelTime1;
    private JLabel labelTime2;
    private JLabel labelTime3;
    private JLabel labelTime4;
    private JLabel labelTime5;
    private JLabel labelTime6;
    private JLabel labelTime7;
    private JLabel labelTime8;
    private JLabel labelTime9;
    private JLabel labelTime10;
    private JLabel labelTimer;

    private MijnTekenPanel mijnTekenPanel;

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Serie s : dl.geefSeries()){
            comboBoxSerie.addItem(s.toString());
        }
    }


    public SimulatieGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);

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
        buttonSimulatie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mijnTekenPanel.startSimulatie();
                tekenPanel = mijnTekenPanel;

            }
        });
    }

    public static void main(String[] args) throws SQLException {

        JFrame frame = new JFrame("SimulatieGUI");
        SimulatieGUI simGUI = new SimulatieGUI(frame);
        frame.setContentPane(new SimulatieGUI(frame).mainPanelSimulatie);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simGUI.createUIComponents();
        frame.setTitle("Simulatie");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    private void createUIComponents() throws SQLException {
        mijnTekenPanel = new MijnTekenPanel();
        tekenPanel = mijnTekenPanel;

    }
}
