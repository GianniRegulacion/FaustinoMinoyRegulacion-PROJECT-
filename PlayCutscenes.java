
package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;

public class PlayCutscenes implements MouseListener {
    private JFrame frame;
    private JLabel arrowButton;
    private JLabel backButton;
    private JLabel playButton;
    private JLabel background;
    private ImageIcon playIcon;  // New Play button
    private ImageIcon arrowIcon;
    private ImageIcon backIcon;
    private ArrayList<ImageIcon> scenes;
    private int currentSceneIndex;
    private Clip bgMusic;

    public PlayCutscenes() {
        // Initialize components
        scenes = new ArrayList<>();
        loadScenes();

        arrowIcon = new ImageIcon("resources/arrowb.png");
        backIcon = new ImageIcon("resources/backb.png");
        playIcon = new ImageIcon("resources/newgame.png");  // Initialize Play button
        
        ImageIcon scaledArrowIcon = new ImageIcon(arrowIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        ImageIcon scaledBackIcon = new ImageIcon(backIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        ImageIcon scaledPlayIcon = new ImageIcon(playIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

        arrowButton = new JLabel(scaledArrowIcon);
        backButton = new JLabel(scaledBackIcon);
        playButton = new JLabel(scaledPlayIcon);

        background = new JLabel();
        background.setIcon(scenes.get(0)); // Set the first scene as the initial background

        frame = new JFrame("Play Cutscene");
        frame.setContentPane(background);
        frame.setLayout(null);

        playBackgroundMusic("resources/rizz.wav");
    }

    public void setFrame() {
        // Set up frame size and layout
        frame.setSize(1920, 1080);
        frame.setLayout(new GraphPaperLayout(new Dimension(50, 30)));

        frame.add(backButton, new Rectangle(1, 1, 7, 5));
        frame.add(arrowButton, new Rectangle(38, 25, 7, 5));
        frame.add(playButton, new Rectangle(38, 25, 7, 5));  // Adjust position of the Play button
        
        playButton.setVisible(false);  // Initially hide the Play button

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // Add listeners
        arrowButton.addMouseListener(this);
        backButton.addMouseListener(this);
        playButton.addMouseListener(this);  // Add mouse listener to Play button

        frame.setVisible(true);
    }

    private void loadScenes() {
        // Load the sequence of scenes
        scenes.add(new ImageIcon("resources/scene1.png"));
        scenes.add(new ImageIcon("resources/scene2.png"));
        scenes.add(new ImageIcon("resources/scene3.png"));
        scenes.add(new ImageIcon("resources/scene4.png"));
        scenes.add(new ImageIcon("resources/scene5.png"));

        // Add more scenes as needed

        currentSceneIndex = 0;
    }

    private void updateScene() {
        if (currentSceneIndex < scenes.size()) {
            background.setIcon(scenes.get(currentSceneIndex));
        } else {
            // Instead of directly launching Play, show the Play button after 5 scenes
            if (currentSceneIndex >= 5) {
                playButton.setVisible(true);  // Show the Play button
                arrowButton.setVisible(false);
            }
        }
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
        if (e.getSource() == arrowButton) {
            currentSceneIndex++;
            updateScene();
        } else if (e.getSource() == backButton) {
            ProjectInterface projectInterface = new ProjectInterface();
            projectInterface.setFrame();
            stopBackgroundMusic();
            frame.dispose();
        } else if (e.getSource() == playButton) {
            Play play = new Play();
            play.setFrame();
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
        PlayCutscenes playCutscene = new PlayCutscenes();
        playCutscene.setFrame();
    }
}