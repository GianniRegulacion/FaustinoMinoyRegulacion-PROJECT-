package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Settings implements MouseListener {
    JFrame frame;
    JLabel Volume;
    JLabel FST;
    JLabel Back;
    ImageIcon VolumeIcon;
    ImageIcon FSTIcon;
    ImageIcon BackIcon;

    private boolean isAudioOn = false; // Tracks audio state
    private Clip backgroundClip; // Clip for audio
    private boolean isFullScreen = true; // Tracks fullscreen state

    public Settings() {
        // Initialize Volume button
        VolumeIcon = new ImageIcon("resources/volume.png");
        ImageIcon scaledVolumeIcon = new ImageIcon(VolumeIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Volume = new JLabel(scaledVolumeIcon);

        // Initialize FST button
        FSTIcon = new ImageIcon("resources/fst.png");
        ImageIcon scaledFSTIcon = new ImageIcon(FSTIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        FST = new JLabel(scaledFSTIcon);

        // Initialize Back button
        BackIcon = new ImageIcon("resources/back.png");
        ImageIcon scaledBackIcon = new ImageIcon(BackIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Back = new JLabel(scaledBackIcon);

        // Set up the frame
        frame = new JFrame("Settings");
        frame.setContentPane(new JLabel(new ImageIcon("resources/gyat.png")));

        // Load audio
        loadAudio("resources/rizz.wav");  // Changed file extension to .wav
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(70, 30)));
        frame.add(Volume, new Rectangle(26, 13, 17, 9));
        frame.add(FST, new Rectangle(26, 16, 17, 9));
        frame.add(Back, new Rectangle(0,0, 17, 9));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setResizable(false);
        addListeners();
    }

    private void loadAudio(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the audio
            backgroundClip.stop(); // Start with audio off
        } catch (Exception e) {
            System.out.println("Error loading audio: " + e.getMessage());
        }
    }

    public void addListeners() {
        Volume.addMouseListener(this);
        FST.addMouseListener(this);
        Back.addMouseListener(this);
    }

    @Override
public void mouseClicked(MouseEvent e) {
    if (e.getSource() == Volume) {
        // Toggle audio
        if (isAudioOn) {
            backgroundClip.stop();  // Stop audio if it's on
            isAudioOn = false;
        } else {
            backgroundClip.setFramePosition(0); // Reset audio to the beginning
            backgroundClip.start();  // Start audio
            isAudioOn = true;
        }
    } else if (e.getSource() == FST) {
        // Toggle fullscreen
        if (isFullScreen) {
            frame.setSize(800, 600);  // Set smaller size
            frame.setLocationRelativeTo(null); // Center the window
            resizeComponents(800, 600);  // Resize components
            isFullScreen = false;
        } else {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Set fullscreen
            resizeComponents(1920, 1080);  // Resize components
            isFullScreen = true;
        }
    } else if (e.getSource() == Back) {
        // Navigate back to ProjectInterface
        frame.dispose(); // Close current settings window
        new ProjectInterface().setFrame(); // Assuming ProjectInterface has a similar setFrame() method
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

    private void resizeComponents(int width, int height) {
        // Scale the components to fit the new window size while maintaining the same aspect ratio
        double widthRatio = (double) width / 1920;
        double heightRatio = (double) height / 1080;

        // Resize Volume button
        Volume.setPreferredSize(new Dimension((int) (300 * widthRatio), (int) (170 * heightRatio)));

        // Resize FST button
        FST.setPreferredSize(new Dimension((int) (300 * widthRatio), (int) (170 * heightRatio)));

        // Resize Back button
        Back.setPreferredSize(new Dimension((int) (300 * widthRatio), (int) (170 * heightRatio)));

        // You can resize more components here based on their roles

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.setFrame();
    }
}
