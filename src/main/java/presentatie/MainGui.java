package presentatie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Project_OODB_ThibaultViaene_0.1 : ZwemwedstrijdenGUI
 *
 * @author viaen
 * @version 28/05/2023
 */
public class MainGui {
    public JPanel mainPanel;
    private JButton buttonZwembadAanmaken;
    private JButton buttonWedstrijdAanmaken;
    private JButton buttonJuryAanmaken;
    private JButton buttonProgrammaAanmaken;
    private JButton buttonOverzicht;
    private JButton buttonSessieAanmaken;
    private JButton buttonZwemmersToevoegen;
    private JButton buttonSimulatie;

    public MainGui(JFrame surroundingFrame) {
        buttonZwembadAanmaken.addActionListener(e -> {
            JFrame frame = new JFrame("ZwembadGUI");
            try {
                frame.setContentPane(new ZwembadGUI(frame).mainPanelZwb);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Zwembad aanmaken");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            surroundingFrame.dispose();
        });
        buttonWedstrijdAanmaken.addActionListener(e -> {
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
        buttonJuryAanmaken.addActionListener(e -> {
            JFrame frame = new JFrame("JuryGUI");
            try {
                frame.setContentPane(new JuryGUI(frame).manPanelJury);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Jury Toevoegen");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            surroundingFrame.dispose();
        });
        buttonProgrammaAanmaken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("WedstrijdProgrammaGUI");
                try {
                    frame.setContentPane(new WedstrijdProgrammaGUI(frame).mainWedstrijdProgrammaPanel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Wedstrijdprogramma Toevoegen");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                surroundingFrame.dispose();
            }
        });
        buttonSessieAanmaken.addActionListener(e -> {
            JFrame frame = new JFrame("SerieAanmakenGUI");
            try {
                frame.setContentPane(new SerieAanmakenGUI(frame).mainPanelSerieAanmaken);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
        });
        buttonZwemmersToevoegen.addActionListener(e -> {
            JFrame frame = new JFrame("ZwemmersToevoegen");
            try {
                frame.setContentPane(new ZwemmersToevoegenGUI(frame).mainPanelZwemmersToevoegen);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
        });
        buttonOverzicht.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("OverzichtGUI");
                try {
                    frame.setContentPane(new OverzichtGUI(frame).mainPanelOverzicht);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Overzicht");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                surroundingFrame.dispose();
            }
        });
        buttonSimulatie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("SimulatieGUI");
                try {
                    frame.setContentPane(new SimulatieGUI(frame).mainPanelSimulatie);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Simulatie");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                surroundingFrame.dispose();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainGUI");
        frame.setContentPane(new MainGui(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Home");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
