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
        boardPanel.setSize(1080, height);
        boardPanel.setLocation(0, 0);
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(boardPanel);
    }
    
    private void initializeControlPanel() {
        
    }
}