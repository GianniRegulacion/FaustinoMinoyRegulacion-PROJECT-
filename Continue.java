/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yuelt
 */

package ProjectMinoyFaustinoRegulacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Continue implements MouseListener {
    JFrame frame;
    JLabel Back;
    JLabel chap1;
    JLabel chap2;
    JLabel game1;
    JLabel game2;
    JLabel next;
    ImageIcon BackIcon;
    ImageIcon chap1Icon;
    ImageIcon chap2Icon;
    ImageIcon game1Icon;
    ImageIcon game2Icon;
    ImageIcon nextIcon;
   
    public Continue() {
        // Initialize chap 1 button
        chap1Icon = new ImageIcon("resources/chap1.png");
        ImageIcon scaledchap1Icon = new ImageIcon(chap1Icon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        chap1 = new JLabel(scaledchap1Icon);
 
        // Initialize chap 2 button
        chap2Icon = new ImageIcon("resources/chap2.png");
        ImageIcon scaledchap2Icon = new ImageIcon(chap2Icon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        chap2 = new JLabel(scaledchap2Icon);

        // Initialize game 1 button
        game1Icon = new ImageIcon("resources/game1.png");
        ImageIcon scaledgame1Icon = new ImageIcon(game1Icon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        game1 = new JLabel(scaledgame1Icon);
       
        // Initialize game 2 button
        game2Icon = new ImageIcon("resources/game2.png");
        ImageIcon scaledgame2Icon = new ImageIcon(game2Icon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        game2 = new JLabel(scaledgame2Icon);
       
        // Initialize Back button
        BackIcon = new ImageIcon("resources/back.png");
        ImageIcon scaledBackIcon = new ImageIcon(BackIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        Back = new JLabel(scaledBackIcon);
       
        // Initialize next button
        nextIcon = new ImageIcon("resources/next.png");
        ImageIcon scalednextIcon = new ImageIcon(nextIcon.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH));
        next = new JLabel(scalednextIcon);

        // Set up the frame
        frame = new JFrame("Continue");
        frame.setContentPane(new JLabel(new ImageIcon("resources/contBg.png")));
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(70, 30)));
        frame.add(chap1, new Rectangle(15, 12, 17, 9));
        frame.add(chap2, new Rectangle(15, 20, 17, 9));
        frame.add(game1, new Rectangle(41,12, 17, 9));
        frame.add(game2, new Rectangle(41, 20, 17, 9));
        frame.add(Back, new Rectangle(1,0, 17, 9));
        frame.add(next, new Rectangle(55,24, 17, 9));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setResizable(false);
        addListeners();
    }

    public void addListeners() {
        Back.addMouseListener(this);
        next.addMouseListener(this);
        chap1.addMouseListener(this);
        chap2.addMouseListener(this);
        game1.addMouseListener(this);
        game2.addMouseListener(this);
    }

    @Override
public void mouseClicked(MouseEvent e) {
    if (e.getSource() == chap1) {

       
    } else if (e.getSource() == chap2) {
       
    } else if (e.getSource()== game1) {
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
    } else if (e.getSource()== game2) {
       
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

    public static void main(String[] args) {
        Continue continues = new Continue();
        continues.setFrame();
    }
}