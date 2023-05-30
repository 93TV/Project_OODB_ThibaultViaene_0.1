package presentatie;

import javax.swing.*;

public class WedstrijdGUI {
    private JPanel mainPanelWedstrijd;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton ButtonWedstrijdAanmaken;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JLabel labelErrorWedstrijd;


    public static void main(String[] args) {
        JFrame frame = new JFrame("WedstrijdGUI");
        frame.setContentPane(new WedstrijdGUI().mainPanelWedstrijd);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wedstrijd Aanmaken");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


