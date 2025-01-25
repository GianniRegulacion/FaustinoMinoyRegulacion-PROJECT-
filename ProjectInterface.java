package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProjectInterface implements MouseListener {
    JFrame frame;
    JLabel PlayCutscenes;
    ImageIcon playIcon;
    JLabel Settings;
    ImageIcon settingsIcon;
    JLabel Exit;
    ImageIcon exitIcon;
    ImageIcon gameBGIcon;
    PlaySound click;
    JLabel Continue;
    ImageIcon continueIcon;

    public ProjectInterface() {
        click = new PlaySound();

        // Play button image resized to 300x170
        playIcon = new ImageIcon("resources/newgame.png");
        ImageIcon scaledPlayIcon = new ImageIcon(playIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        PlayCutscenes = new JLabel(scaledPlayIcon);

        // Settings button image resized to 300x170
        settingsIcon = new ImageIcon("resources/settings.png");
        ImageIcon scaledSettingsIcon = new ImageIcon(settingsIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Settings = new JLabel(scaledSettingsIcon);

        // Exit button image resized to 300x170
        exitIcon = new ImageIcon("resources/exit.png");
        ImageIcon scaledExitIcon = new ImageIcon(exitIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Exit = new JLabel(scaledExitIcon);
        
        //Continue button basta uy
        continueIcon = new ImageIcon("resources/cont.png");
        ImageIcon scaledContinueIcon = new ImageIcon(continueIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Continue = new JLabel(scaledContinueIcon);

        // Set up the frame with the resized background image
        frame = new JFrame("Skibidi Launcher");
        frame.setContentPane(new JLabel(new ImageIcon("resources/mainmenu.gif")));
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(50, 30)));
        frame.add(PlayCutscenes, new Rectangle(19, 12, 13, 5));
        frame.add(Continue, new Rectangle(19, 15, 13, 5));
        frame.add(Settings, new Rectangle(19, 18, 13, 5));
        frame.add(Exit, new Rectangle(19, 21, 13, 5));
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setResizable(false);
        addListeners();
    }

    public void addListeners() {
        PlayCutscenes.addMouseListener(this);
        Settings.addMouseListener(this);
        Exit.addMouseListener(this);
        Continue.addMouseListener(this);

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
        } else if (e.getSource() == PlayCutscenes) {
            // Call PlayCutscenes instead of creating a new frame
            PlayCutscenes playCutscenes = new PlayCutscenes();
            playCutscenes.setFrame(); // Execute the relevant method from PlayCutscenes
        } else if (e.getSource() == Continue) {
            // Create an instance of Settings and call setFrame() to show the settings window
            Continue continues = new Continue();
            continues.setFrame();
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
