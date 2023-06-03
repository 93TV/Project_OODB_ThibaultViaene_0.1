package presentatie;

import javax.swing.*;
import java.awt.*;

public class Animatie extends JPanel {
    private ImageIcon[] frames; // Array to store individual frames of the animation
    private int currentFrameIndex; // Index of the currently displayed frame
    private Timer timer; // Timer to control the animation speed

    public Animatie() {
        frames = new ImageIcon[4]; // Create an array of ImageIcons to hold the frames
        currentFrameIndex = 0;

        // Load the frames from the PNG file
        frames[0] = new ImageIcon("src/main/java/recources/SwimA1.png");
        frames[1] = new ImageIcon("src/main/java/recources/SwimA2.png");
        frames[2] = new ImageIcon("src/main/java/recources/SwimA3.png");
        frames[3] = new ImageIcon("src/main/java/recources/SwimA4.png");

        int delay = 200; // Delay between frame changes in milliseconds
        timer = new Timer(delay, e -> {
            currentFrameIndex = (currentFrameIndex + 1) % frames.length; // Increment frame index
            repaint(); // Request the panel to be repainted
        });
    }

    public void startAnimation() {
        timer.start(); // Start the animation timer
    }

    public void stopAnimation() {
        timer.stop(); // Stop the animation timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image currentFrame = frames[currentFrameIndex].getImage();
        g.drawImage(currentFrame, 0, 0, this); // Draw the current frame on the panel
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Animatie player = new Animatie();
        frame.add(player);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        player.startAnimation(); // Start the animation
    }
}
