package presentatie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project_OODB_ThibaultViaene_0.1 : ZwemwedstrijdenGUI
 *
 * @author viaen
 * @version 28/05/2023
 */
public class mainGUI {
    public JPanel mainPanel;
    private JButton buttonZwembadAanmaken;
    private JButton button1;
    private JButton button2;

    public mainGUI(JFrame surroundingFrame) {
        buttonZwembadAanmaken.addActionListener(e -> {
            JFrame frame = new JFrame("ZwembadGUI");
            frame.setContentPane(new ZwembadGUI(frame).mainPanelZwb);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Zwembad aanmaken");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            surroundingFrame.dispose(); //frame van deze form verwijderen
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
