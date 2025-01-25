package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;

public class Congrats implements MouseListener {
    private JFrame frame;
    private JLabel playButton;
    private JLabel background;
    private ImageIcon playIcon;
    private Clip bgMusic;

    public Congrats() {
        // Initialize components
        playIcon = new ImageIcon("resources/play.png"); // Play button icon

        ImageIcon scaledPlayIcon = new ImageIcon(playIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

        playButton = new JLabel(scaledPlayIcon);
        background = new JLabel();
        background.setIcon(new ImageIcon("resources/congratsscene.png")); // Set congratulatory background

        frame = new JFrame("Congratulations!");
        frame.setContentPane(background);
        frame.setLayout(null);

        playBackgroundMusic("resources/victory.wav"); // Play background music
    }

    public void setFrame() {
        // Set up frame size and layout
        frame.setSize(1920, 1080);
        frame.setLayout(new GraphPaperLayout(new Dimension(50, 30)));

        frame.add(playButton, new Rectangle(20, 9, 7, 5)); // Add Play button

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // Add listener
        playButton.addMouseListener(this);

        frame.setVisible(true);
    }

    private void playBackgroundMusic(String filepath) {
        try {
            File musicPath = new File(filepath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                bgMusic = AudioSystem.getClip();
                bgMusic.open(audioInput);
                bgMusic.start();
                bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Music file not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (bgMusic != null && bgMusic.isRunning()) {
            bgMusic.stop();
            bgMusic.close();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == playButton) {
            PlayCutscenes2 playCutscenes2 = new PlayCutscenes2(); // Redirect to PlayCutscenes2
            playCutscenes2.setFrame();
            stopBackgroundMusic();
            frame.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Optional: Add behavior for mouse press
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Optional: Add behavior for mouse release
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Optional: Add hover effects
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Optional: Remove hover effects
    }

    public static void main(String[] args) {
        Congrats congrats = new Congrats();
        congrats.setFrame();
    }
}
