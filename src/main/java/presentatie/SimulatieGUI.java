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
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;
    private Timer timer4;
    private Timer timer5;
    private Timer timer6;
    private Timer timer7;
    private Timer timer8;
    private Timer timer9;
    private Timer timer10;

    private void updateTimer(long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTimer.setText(formattedTime);
    }

    private void updateTimer1(long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime1.setText(formattedTime);
    }

    private void updateTimer2(long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime2.setText(formattedTime);
    }

    private void updateTimer3 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime3.setText(formattedTime);
    }

    private void updateTimer4 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime4.setText(formattedTime);
    }
    private void updateTimer5 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime5.setText(formattedTime);
    }
    private void updateTimer6 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime6.setText(formattedTime);
    }
    private void updateTimer7 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime7.setText(formattedTime);
    }
    private void updateTimer8 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime8.setText(formattedTime);
    }
    private void updateTimer9 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime9.setText(formattedTime);
    }
    private void updateTimer10 (long startTime) {
        Duration elapsedTime = Duration.ofSeconds(System.currentTimeMillis() / 1000 - startTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                elapsedTime.toHoursPart(),
                elapsedTime.toMinutesPart(),
                elapsedTime.toSecondsPart());
        labelTime10.setText(formattedTime);
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
                        }

                    }
                });
                timer.setInitialDelay(0);
                timer.start();

                timer1 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer1(startTime);
                        if (mijnTekenPanel.zwemmer1Stopped()) {

                            timer1.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 1) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime1.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 1));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer1.setInitialDelay(0);
                timer1.start();

                timer2 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer2(startTime);
                        if (mijnTekenPanel.zwemmer2Stopped()) {
                            timer2.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 2) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime2.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 2));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer2.setInitialDelay(0);
                timer2.start();

                timer3 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer3(startTime);
                        if (mijnTekenPanel.zwemmer3Stopped()) {
                            timer3.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 3) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime3.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 3));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer3.setInitialDelay(0);
                timer3.start();

                timer4 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer4(startTime);
                        if (mijnTekenPanel.zwemmer4Stopped()) {
                            timer4.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 4) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime4.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 4));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer4.setInitialDelay(0);
                timer4.start();

                timer5 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer5(startTime);
                        if (mijnTekenPanel.zwemmer5Stopped()) {
                            timer5.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 5) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime5.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 5));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer5.setInitialDelay(0);
                timer5.start();

                timer6 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer6(startTime);
                        if (mijnTekenPanel.zwemmer6Stopped()) {
                            timer6.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 6) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime6.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 6));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer6.setInitialDelay(0);
                timer6.start();

                timer7 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer7(startTime);
                        if (mijnTekenPanel.zwemmer7Stopped()) {
                            timer7.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 7) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime7.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 7));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer7.setInitialDelay(0);
                timer7.start();

                timer8 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer8(startTime);
                        if (mijnTekenPanel.zwemmer8Stopped()) {
                            timer8.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 8) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime8.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 8));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer8.setInitialDelay(0);
                timer8.start();

                timer9 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer9(startTime);
                        if (mijnTekenPanel.zwemmer9Stopped()) {
                            timer9.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 9) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime9.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 9));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer9.setInitialDelay(0);
                timer9.start();

                timer10 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimer10(startTime);
                        if (mijnTekenPanel.zwemmer10Stopped()) {
                            timer10.stop();
                            try {
                                if (dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex() +1, 10) != -1){
                                    dl.insertResultatenSim(comboBoxSerie.getSelectedIndex() + 1,  labelTime10.getText(), dl.getZwemmerIdSerieBaan(comboBoxSerie.getSelectedIndex()+1, 10));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });
                timer10.setInitialDelay(0);
                timer10.start();


            }
        });


        comboBoxSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mijnTekenPanel.setLengteZwembad(dl.getLengteZwembad(comboBoxSerie.getSelectedIndex() +1));
                    mijnTekenPanel.setAfstandComp(dl.getAfstandComp(comboBoxSerie.getSelectedIndex() + 1));

                    mijnTekenPanel.setAantalZwemmers(dl.getAantalZwemmers(comboBoxSerie.getSelectedIndex() + 1));
                    mijnTekenPanel.repaint();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

//    private void resetAllTimers() {
//        labelTimer.setText("00:00:00");
//        labelTime1.setText("00:00:00");
//        labelTime2.setText("00:00:00");
//        labelTime3.setText("00:00:00");
//        labelTime4.setText("00:00:00");
//        labelTime5.setText("00:00:00");
//        labelTime6.setText("00:00:00");
//        labelTime7.setText("00:00:00");
//        labelTime8.setText("00:00:00");
//        labelTime9.setText("00:00:00");
//        labelTime10.setText("00:00:00");
//    }


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
