package presentatie;

import data.DataLaag;
import logica.Serie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;

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
    private Timer timer;

    private void updateTimer(long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTimer.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer1Stopped()) labelTime1.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer2Stopped()) labelTime2.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer3Stopped()) labelTime3.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer4Stopped()) labelTime4.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer5Stopped()) labelTime5.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer6Stopped()) labelTime6.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer7Stopped()) labelTime7.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer8Stopped()) labelTime8.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer9Stopped()) labelTime9.setText(formattedTime);
        if (!mijnTekenPanel.zwemmer10Stopped()) labelTime10.setText(formattedTime);
    }

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Serie s : dl.geefSeries()) {
            comboBoxSerie.addItem(s.toString());
        }
    }

    public SimulatieGUI(JFrame surroundingFrame) throws SQLException {
        DataLaag dl = new DataLaag();
        comboVuller(dl);
        mijnTekenPanel.setAantalZwemmers(dl.getAantalZwemmers(comboBoxSerie.getSelectedIndex() + 1));


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


                long startTime = System.currentTimeMillis() / 1000;

                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer(startTime);
                        if (mijnTekenPanel.alleZwemmersFinished()) {
                            timer.stop();
                            try {
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime1.getText(), 0);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime2.getText(), 1);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime3.getText(), 2);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime4.getText(), 3);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime5.getText(), 4);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime6.getText(), 5);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime7.getText(), 6);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime8.getText(), 7);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime9.getText(), 8);
                                dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1, labelTime10.getText(), 9);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                        }

                    }
                });
                timer.setInitialDelay(0);
                timer.start();

            }
        });


        comboBoxSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mijnTekenPanel.setLengteZwembad(dl.getLengteZwembad(comboBoxSerie.getSelectedIndex() + 1));
                    mijnTekenPanel.setAfstandComp(dl.getAfstandComp(comboBoxSerie.getSelectedIndex() + 1));

                    mijnTekenPanel.setAantalZwemmers(dl.getAantalZwemmers(comboBoxSerie.getSelectedIndex() + 1));
                    mijnTekenPanel.repaint();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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


    void createUIComponents() throws SQLException {
        mijnTekenPanel = new MijnTekenPanel();
        tekenPanel = mijnTekenPanel;
    }
}
