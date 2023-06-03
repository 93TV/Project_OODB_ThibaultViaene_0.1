package logica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Zwemmer {

        private int x;
        private int y;
        private int grootte;
        private int snelheid = 1;
        private int richting;

        private Image img;

        public Zwemmer(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.grootte = size;
            this.richting = 1;
            try {
                img = ImageIO.read(new File("src/main/java/recources/SwimA1.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
            richting *= -1;
        }

        public void beweeg() {
            x += richting * snelheid;;
        }

    public Image getImg() {
            return img;
    }
}

