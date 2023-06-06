package presentatie;

import logica.Zwemmer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Animatie extends JPanel {
    private Image backgroundImage;
    private Image boat;
    private Image fishes;

    private Image fishingHut;

    private Image chest;
    private ImageIcon[] frames;
    private int currentFrameIndex;
    private Timer timer;
    private Zwemmer z;
    private Zwemmer fish;
    private Zwemmer bigFish;
    private Image grass;
    private Image bigFishImage;

    private boolean initialized = false;

    public Animatie() {
        backgroundImage = new ImageIcon("src/main/java/recources/Water.png").getImage();
        boat = new ImageIcon("src/main/java/recources/Boat.png").getImage();
        fishes = new ImageIcon("src/main/java/recources/5.png").getImage();
        bigFishImage = new ImageIcon("src/main/java/recources/6.png").getImage();
        chest = new ImageIcon("src/main/java/recources/Chest.png").getImage();
        grass = new ImageIcon("src/main/java/recources/grass3.png").getImage();
        fishingHut = new ImageIcon("src/main/java/recources/Fishing_hut.png").getImage();

        z = new Zwemmer(0, 0, 30, 0.5);
        fish = new Zwemmer(0,0,30, 0.25);
        bigFish = new Zwemmer(0,0200,60, 0.20);
        frames = new ImageIcon[6];

        currentFrameIndex = 0;
        frames[0] = new ImageIcon("src/main/java/recources/SwimA1.png");
        frames[1] = new ImageIcon("src/main/java/recources/SwimA2.png");
        frames[2] = new ImageIcon("src/main/java/recources/SwimA3.png");
        frames[3] = new ImageIcon("src/main/java/recources/SwimA4.png");
        frames[4] = new ImageIcon("src/main/java/recources/SwimA5.png");
        frames[5] = new ImageIcon("src/main/java/recources/SwimA6.png");
        int delay = 200;
        timer = new Timer(delay, e -> {
            currentFrameIndex = (currentFrameIndex + 1) % frames.length;
            repaint();
        });
    }

    public void startAnimation() {
        timer.start();

        Timer timer2 = new Timer(10, e -> {
            zwem();
            repaint();
        });
        timer2.start();
        new Thread(() -> startmusic()).start();
    }


    private void zwem() {
        if (z.getX() > this.getWidth()) {
            z = new Zwemmer(-z.getGrootte(), 0, 30, 0.5);

        }
        if (fish.getX() > this.getWidth()){
            fish = new Zwemmer(-fish.getGrootte(), 0, 30,0.25);
        }
        if (bigFish.getX() > this.getWidth()){
            bigFish = new Zwemmer(-bigFish.getGrootte(), 0, 60,0.20);
        }
        z.beweeg();
        fish.beweeg();
        bigFish.beweeg();
    }

    public void stopAnimation() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int imageWidth = backgroundImage.getWidth(this);
        int imageHeight = backgroundImage.getHeight(this);

        for (int x = 0; x < getWidth(); x += imageWidth) {
            for (int y = 0; y < getHeight(); y += imageHeight) {
                g.drawImage(backgroundImage, x, y, this);
            }
        }
        g.drawImage(boat, this.getWidth() / 2, this.getHeight() / 2, null);
        g.drawImage(chest, this.getWidth() - 100, 150, null);
        g.drawImage(grass, 50, 40, null);
        g.drawImage(fishingHut, -50, 100, null);
        Image currentFrame = frames[currentFrameIndex].getImage();
        g.drawImage(currentFrame, z.getX(), 100, this);
        g.drawImage(fishes, fish.getX(), 10, this);
        g.drawImage(bigFishImage, bigFish.getX(), 175, this);


    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Welcom In Paradise!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Animatie animatie = new Animatie();
        frame.add(animatie);
        frame.pack();
        frame.setSize(500, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        animatie.startAnimation();


    }

    private static void startmusic() {
        String audioFilePath = "src/main/java/recources/war.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            while (!clip.isRunning()) {
                Thread.sleep(10);
            }
            while (clip.isRunning()) {
                Thread.sleep(10);
            }
            clip.close();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
