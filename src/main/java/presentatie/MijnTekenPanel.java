package presentatie;

import logica.Zwemmer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MijnTekenPanel extends javax.swing.JPanel {
    private int aantalBanen = 10;
    private int afstandComp;
    private int lengteZwembad;
    private int aantalLengtes;
    private int aantalZwemmers;
    private ArrayList<Zwemmer> zwemmers;
    private int grootteZwemmer = 100;
    private boolean initialized;
    private int gestopteZwemmers = 0;

    public MijnTekenPanel() {
        initialized = false;
    }



    private void initialiseerZwemmers() {
        zwemmers = new ArrayList<>();
        int height = this.getHeight();
        int baan = height / aantalBanen;
        for (int i = 0; i < aantalZwemmers; i++) {
            zwemmers.add(new Zwemmer(0, i*baan - grootteZwemmer/3 , grootteZwemmer));
        }
    }

    public boolean alleZwemmersFinished() {
        return gestopteZwemmers == aantalZwemmers;
    }

    private void tekenZwemmers(Graphics g){
        g.setColor(Color.black);
        for (Zwemmer z : zwemmers) {
            g.drawImage(z.getImg(), z.getX(), z.getY(), z.getGrootte(), z.getGrootte(), null);

        }
    }

    public void startSimulatie() {

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
                if (!z.isGestopt()){
                    if (z.getAfgelegdeLengte() == aantalLengtes) {
                        z.stop();
                        z.setGestopt(true);
                        gestopteZwemmers++;
                    }
                }

            }
            z.beweeg();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        if (!initialized) {
//            initialiseerZwemmers();
//            initialized = true;
//        }

        int width = this.getWidth();
        int height = this.getHeight();
        int baan = height / aantalBanen;

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.RED);
        for (int i = 0; i < height; i = i + baan) {
            g.drawLine(0, i, width, i);
        }

        tekenZwemmers(g);
    }

    public boolean zwemmer1Stopped() {
        return zwemmers.get(0).isGestopt();
    }

    public boolean zwemmer2Stopped() {
        return zwemmers.get(1).isGestopt();
    }
    public boolean zwemmer3Stopped() {
        return zwemmers.get(2).isGestopt();
    }

    public boolean zwemmer4Stopped() {
        return zwemmers.get(3).isGestopt();
    }
    public boolean zwemmer5Stopped() {
        return zwemmers.get(4).isGestopt();
    }

    public boolean zwemmer6Stopped() {
        return zwemmers.get(5).isGestopt();
    }
    public boolean zwemmer7Stopped() {
        return zwemmers.get(6).isGestopt();
    }

    public boolean zwemmer8Stopped() {
        return zwemmers.get(7).isGestopt();
    }
    public boolean zwemmer9Stopped() {
        return zwemmers.get(8).isGestopt();
    }

    public boolean zwemmer10Stopped() {
        return zwemmers.get(9).isGestopt();
    }


    public void setAfstandComp(int afstandComp) {
        this.afstandComp = afstandComp;
        this.aantalLengtes = this.afstandComp / this.lengteZwembad;
    }


    public void setLengteZwembad(int lengteZwembad) {
        this.lengteZwembad = lengteZwembad;
    }

    public void setAantalZwemmers(int aantalZwemmers) {
        this.aantalZwemmers = aantalZwemmers;
        initialiseerZwemmers();
        initialized = true;
    }



}
