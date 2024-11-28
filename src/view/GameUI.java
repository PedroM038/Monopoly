package view;

import java.awt.*;
import javax.swing.*;

public class GameUI extends JFrame {

    int height = 1080;
    int width = 1920;

    public GameUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.getContentPane().setBackground(java.awt.Color.WHITE);
        this.setLayout(null);
        initializeBoardPanel();
        initializeControlPanel();

        this.setVisible(true);
    }

    private void initializeBoardPanel() {
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);
        boardPanel.setSize(960, height);
        boardPanel.setLocation(0, 0);
        this.add(boardPanel);
    }
    
    private void initializeControlPanel() {
        JPanel turnPanel = new JPanel();
        turnPanel.setBackground(Color.WHITE);
        turnPanel.setSize(960, 450);
        turnPanel.setLocation(960, 0);
        turnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(turnPanel);

        JPanel spacePanel = new JPanel();
        Color color = new Color(0, 100, 25, 150);
        spacePanel.setBackground(color);
        spacePanel.setSize(960, 450);
        spacePanel.setLocation(960, 450);
        spacePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(spacePanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setSize(960, 180);
        buttonsPanel.setLocation(960, 900);
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(buttonsPanel);
    }
}