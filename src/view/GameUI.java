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
        CustomBoardPanel boardPanel = new CustomBoardPanel();
        boardPanel.setSize(1000, height);
        boardPanel.setLocation(0, 0);
        boardPanel.setBackground(Color.BLACK);
        this.add(boardPanel);
    }
    
    private void initializeControlPanel() {
        JPanel turnPanel = new JPanel();
        turnPanel.setBackground(Color.WHITE);
        turnPanel.setSize(920, 450);
        turnPanel.setLocation(1000, 0);
        turnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(turnPanel);

        JPanel spacePanel = new JPanel();
        Color color = new Color(0, 100, 25, 150);
        spacePanel.setBackground(color);
        spacePanel.setSize(920, 450);
        spacePanel.setLocation(1000, 450);
        spacePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(spacePanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setSize(920, 180);
        buttonsPanel.setLocation(1000, 900);
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(buttonsPanel);
    }
}