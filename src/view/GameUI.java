package view;

import java.awt.*;
import javax.swing.*;

public class GameUI extends JFrame {

    int height = 1080;
    int width = 1920;
    private final CustomBoard boardPanel;
    private final CustomControl controlPanel;

    public GameUI() {
        this.setTitle("Monopoly Game");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.boardPanel = new CustomBoard();
        add(boardPanel, BorderLayout.CENTER);

        this.controlPanel = new CustomControl();
        add(controlPanel, BorderLayout.EAST);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public CustomControl getControlPanel() {
        return this.controlPanel;
    }

    public CustomBoard getBoardPanel() {
        return this.boardPanel;

    }
}