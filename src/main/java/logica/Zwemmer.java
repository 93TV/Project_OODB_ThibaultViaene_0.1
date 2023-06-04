package logica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zwemmer {
        private int x;
        private int y;
        private int grootte;
        private double snelheid;
        private int richting;
        private int afgelegdeLengte = 0;
        private Image img;
        private boolean gestopt = false;


    public Zwemmer(int x, int y, int grootte, double snelheid) {
        this.x = x;
        this.y = y;
        this.grootte = grootte;
        this.snelheid = snelheid;
        this.richting = 1;
    }

    public Zwemmer(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.grootte = size;
            this.richting = 1;
            this.snelheid =  Math.random() * 10;
            System.out.println(this.snelheid);
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
            return y;
        }

        public int getGrootte() {
            return grootte;
        }

        public void reverseRichting() {
            if (snelheid != 0) {
                richting *= -1;
                afgelegdeLengte += 1;
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
            x += richting * snelheid;
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

    public int getRichting() {
        return richting;
    }

    public void resetZwemmer() {

    }
}

