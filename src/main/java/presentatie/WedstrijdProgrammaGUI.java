package presentatie;

import data.DataLaag;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WedstrijdProgrammaGUI {


    public JPanel mainWedstrijdProgrammaPanel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton buttonTerug;
    private JButton buttonProgrammaMaken;


    public WedstrijdProgrammaGUI(JFrame surroundingFrame) {
        DataLaag dl = new DataLaag();
        buttonTerug.addActionListener(e -> {
            JFrame frame = new JFrame("mainGUI");
            frame.setContentPane(new MainGui(frame).mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            surroundingFrame.dispose();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WedstrijdProgrammaGUI");
        frame.setContentPane(new WedstrijdProgrammaGUI(frame).mainWedstrijdProgrammaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wedstrijdprogramma Toevoegen");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
