package presentatie;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Project_OODB_ThibaultViaene_0.1 : ZwemwedstrijdenGUI
 *
 * @author viaen
 * @version 28/05/2023
 */
public class mainGUI {
    public JPanel mainPanel;
    private JButton buttonZwembadAanmaken;
    private JButton buttonWedstrijdAanmaken;
    private JButton button2;

    public mainGUI(JFrame surroundingFrame) {
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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainGUI");
        frame.setContentPane(new mainGUI(frame).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
