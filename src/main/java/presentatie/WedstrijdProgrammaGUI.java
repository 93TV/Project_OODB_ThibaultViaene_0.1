package presentatie;

import javax.swing.*;

public class WedstrijdProgrammaGUI {


    private JPanel mainWedstrijdProgrammaPanel;
    private JTextField textField1;
    private JComboBox comboBox1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("WedstrijdProgrammaGUI");
        frame.setContentPane(new WedstrijdProgrammaGUI().mainWedstrijdProgrammaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
