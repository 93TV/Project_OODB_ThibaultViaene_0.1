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

    private void comboVuller(DataLaag dl) throws SQLException {
        for (Serie s : dl.geefSeries()) {
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
