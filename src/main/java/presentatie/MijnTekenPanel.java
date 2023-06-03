package presentatie;

import logica.Zwemmer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MijnTekenPanel extends javax.swing.JPanel {
    private int aantalBanen = 10;
    private int lengte = 100;
    private int aantalLengtes;
    private int aantalZwemmers = 5;
    private ArrayList<Zwemmer> zwemmers;
    private int grootteZwemmer = 50;
    private boolean initialized;

    public MijnTekenPanel() {
        initialized = false;
    }

    private void initialiseerZwemmers() {
        zwemmers = new ArrayList<>();
        int height = this.getHeight();
        int baan = height / aantalBanen;
        for (int i = 0; i < aantalZwemmers; i++) {
            zwemmers.add(new Zwemmer(grootteZwemmer, (baan * i) + grootteZwemmer , grootteZwemmer));
        }
    }

    private void tekenZwemmers(Graphics g){
        g.setColor(Color.black);
        for (Zwemmer z : zwemmers) {

            g.drawImage(z.getImg(), z.getX(), z.getY(), z.getGrootte(), z.getGrootte(), null);
//            g.fillOval(z.getX(), z.getY(), z.getGrootte(), z.getGrootte());
        }
    }

    public void startSimulatie() {
        if (!initialized) {
            initialiseerZwemmers();
            initialized = true;
        }

        Timer timer = new Timer(10, e -> {
            zwem();
            repaint();
        });
        timer.start();
    }

    private void zwem() {
        for (Zwemmer z : zwemmers){
            if (z.getX() + grootteZwemmer > this.getWidth() || z.getX() < 0){
                z.reverseRichting();
            }
            z.beweeg();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!initialized) {
            initialiseerZwemmers();
            initialized = true;
        }

        int width = this.getWidth();
        int height = this.getHeight();
        int baan = height / aantalBanen;

        g.setColor(Color.blue);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.RED);
        for (int i = 0; i < height; i = i + baan) {
            g.drawLine(0, i, width, i);
        }

        tekenZwemmers(g);
    }
}
