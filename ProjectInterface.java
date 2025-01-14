package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProjectInterface implements MouseListener {
    JFrame frame;
    JLabel Play;
    ImageIcon playIcon;
    JLabel Settings;
    ImageIcon settingsIcon;
    JLabel Exit;
    ImageIcon exitIcon;
    ImageIcon gameBGIcon;
    PlaySound click;

    public ProjectInterface() {
        click = new PlaySound();

        // Play button image resized to 300x170
        playIcon = new ImageIcon("resources/newgame.png");
        ImageIcon scaledPlayIcon = new ImageIcon(playIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Play = new JLabel(scaledPlayIcon);

        // Settings button image resized to 300x170
        settingsIcon = new ImageIcon("resources/settings.png");
        ImageIcon scaledSettingsIcon = new ImageIcon(settingsIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Settings = new JLabel(scaledSettingsIcon);

        // Exit button image resized to 300x170
        exitIcon = new ImageIcon("resources/exit.png");
        ImageIcon scaledExitIcon = new ImageIcon(exitIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Exit = new JLabel(scaledExitIcon);

        // Set up the frame with the resized background image
        frame = new JFrame("Skibidi Launcher");
        frame.setContentPane(new JLabel(new ImageIcon("resources/mainmenu.gif")));
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(50, 30)));
        frame.add(Play, new Rectangle(19, 12, 13, 5));
        frame.add(Settings, new Rectangle(19, 15, 13, 5));
        frame.add(Exit, new Rectangle(19, 18, 13, 5));
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setResizable(false);
        addListeners();
    }

    public void addListeners() {
        Play.addMouseListener(this);
        Settings.addMouseListener(this);
        Exit.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Exit) {
            System.out.println("Exit button Clicked");
            frame.dispose(); // Close the main frame
            System.exit(0); // Close EVERYTHING
        } else if (e.getSource() == Settings) {
            // Create an instance of Settings and call setFrame() to show the settings window
            Settings settings = new Settings();
            settings.setFrame();
        } else if (e.getSource() == Play) {
            // Handle Play button click
            JFrame playFrame = new JFrame("Connect the Dots Game"); // Create a new JFrame for Play
            Play playPanel = new Play(); // Create an instance of the Play class

            playFrame.setLayout(new BorderLayout());
            playFrame.add(playPanel, BorderLayout.CENTER);

            JButton restartButton = new JButton("Restart");
            restartButton.addActionListener(event -> {
                playPanel.initializeGame();
                playPanel.repaint();
            });
            playFrame.add(restartButton, BorderLayout.SOUTH);

            playFrame.setSize(600, 600);
            playFrame.setResizable(false);
            playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Point p = frame.getLocation(); // Get current frame's location
            playFrame.setLocation(p); // Set the new frame's location
            playFrame.setVisible(true); // Make the Play frame visible
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
        ProjectInterface projectInterface = new ProjectInterface();
        projectInterface.setFrame();
    }
}
