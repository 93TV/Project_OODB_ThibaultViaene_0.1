package logica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zwemmer {
    private int x;
    private final int Y;
    private final int GROOTTE;
    private double snelheid;
    private int richting;
    private int afgelegdeLengte = 0;
    private Image img;
    private boolean gestopt = false;


    public Zwemmer(int x, int y, int grootte, double snelheid) {
        this.x = x;
        this.Y = y;
        this.GROOTTE = grootte;
        this.snelheid = snelheid;
        this.richting = 6;
    }

    public Zwemmer(int x, int y, int size) {
        this.x = x;
        this.Y = y;
        this.GROOTTE = size;
        this.richting = 6;
        this.snelheid = 0.3 + Math.random() * 0.2;
        try {
            img = ImageIO.read(new File("src/main/java/recources/SwimA1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isGestopt() {
        return gestopt;
    }

    public void setGestopt(boolean gestopt) {
        this.gestopt = gestopt;
    }

    public int getAfgelegdeLengte() {
        return afgelegdeLengte;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return Y;
    }

    public int getGROOTTE() {
        return GROOTTE;
    }

    public void reverseRichting() {
        if (snelheid != 0) {
            richting *= -1;
            afgelegdeLengte += 1;
            snelheid *= (0.9 + Math.random() * 0.1);
            mirrorImage();
        } else {
            try {
                img = ImageIO.read(new File("src/main/java/recources/SwimAFinish.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void beweeg() {
        x += richting * (snelheid * (0.7 + Math.random() * 0.5));
    }

    public void stop() {
        snelheid = 0;
    }

    public Image getImg() {
        return img;
    }

    private void mirrorImage() {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage mirroredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mirroredImage.createGraphics();
        g.drawImage(img, width, 0, -width, height, null);
        g.dispose();
        img = mirroredImage;
    }

}

