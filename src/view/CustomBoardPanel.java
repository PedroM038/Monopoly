package view;

import java.awt.*;
import javax.swing.*;

public class CustomBoardPanel extends JPanel {
    private static final int CELL_SIZE = 120;
    private static final int BOARD_SIZE = 1080;
    private static final int BOARD_MARGIN = 120;
    private static final int SPACES_TOP = 9;
    private static final int SPACES_RIGHT = 8;
    private static final int SPACES_BOTTOM = 8;
    private static final int SPACES_LEFT = 7;
    
    private final Image boardImage;

    public CustomBoardPanel() {
        boardImage = new ImageIcon(getClass().getResource("./assets/background.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawGridLines(g);
        drawSpaceNumbers(g);
        drawAllImageSpace(g);
    }

    private void drawBoard(Graphics g) {
        g.drawImage(boardImage, BOARD_MARGIN, BOARD_MARGIN, this);
        g.drawLine(0, BOARD_MARGIN, BOARD_SIZE, BOARD_MARGIN);
        g.drawLine(BOARD_MARGIN, 0, BOARD_MARGIN, BOARD_SIZE);
        g.drawLine(BOARD_SIZE - BOARD_MARGIN, 0, BOARD_SIZE - BOARD_MARGIN, BOARD_SIZE);
        g.drawLine(0, BOARD_SIZE - BOARD_MARGIN, BOARD_SIZE, BOARD_SIZE - BOARD_MARGIN);
    }

    private void drawGridLines(Graphics g) {
        for (int i = 0; i < 7; i++) {
            int position = BOARD_MARGIN + (i * CELL_SIZE);
            // Vertical lines
            g.drawLine(position, 0, position, BOARD_MARGIN);
            g.drawLine(position, BOARD_SIZE - BOARD_MARGIN, position, BOARD_SIZE);
            // Horizontal lines
            g.drawLine(0, position, BOARD_MARGIN, position);
            g.drawLine(BOARD_SIZE - BOARD_MARGIN, position, BOARD_SIZE, position);
        }
    }

    private void drawSpaceNumbers(Graphics g) {
        // Top row
        drawSpaceRow(g, 0, SPACES_TOP, 20, 60, CELL_SIZE, 0);
        
        // Right column
        drawSpaceRow(g, SPACES_TOP, SPACES_TOP + SPACES_RIGHT, 980, 160, 0, CELL_SIZE);
        
        // Bottom row (reverse)
        drawSpaceRow(g, SPACES_TOP + SPACES_RIGHT, 
                    SPACES_TOP + SPACES_RIGHT + SPACES_BOTTOM, 
                    860, 1000, -CELL_SIZE, 0);
        
        // Left column (reverse)
        drawSpaceRow(g, SPACES_TOP + SPACES_RIGHT + SPACES_BOTTOM,
                    SPACES_TOP + SPACES_RIGHT + SPACES_BOTTOM + SPACES_LEFT,
                    20, 880, 0, -CELL_SIZE);
    }

    private void drawSpaceRow(Graphics g, int startSpace, int endSpace, 
                            int startX, int startY, int deltaX, int deltaY) {
        int x = startX;
        int y = startY;
        for (int i = startSpace; i < endSpace; i++) {
            g.drawString("Espaço: " + i, x, y);
            x += deltaX;
            y += deltaY;
        }
    }

    private void drawAllImageSpace(Graphics g) {
        drawImageSpace(g, 1, 0, 0);
        drawImageSpace(g, 2, 120, 0);
        drawImageSpace(g, 3, 240, 0);
        drawImageSpace(g, 4, 360, 0);
        drawImageSpace(g, 5, 480, 0);
        drawImageSpace(g, 6, 600, 0);
        drawImageSpace(g, 7, 720, 0);
        drawImageSpace(g, 8, 840, 0);
        drawImageSpace(g, 9, 960, 0);
        drawImageSpace(g, 10, 960, 120);
        drawImageSpace(g, 11, 960, 240);
        drawImageSpace(g, 12, 960, 360);
        drawImageSpace(g, 13, 960, 480);
        drawImageSpace(g, 14, 960, 600);
        drawImageSpace(g, 15, 960, 720);
        drawImageSpace(g, 16, 960, 840);
        drawImageSpace(g, 17, 960, 960);
        drawImageSpace(g, 18, 840, 960);
        drawImageSpace(g, 19, 720, 960);
        drawImageSpace(g, 20, 600, 960);
        drawImageSpace(g, 21, 480, 960);
        drawImageSpace(g, 22, 360, 960);
        drawImageSpace(g, 23, 240, 960);
        drawImageSpace(g, 24, 120, 960);
        drawImageSpace(g, 25, 0, 960);
        drawImageSpace(g, 26, 0, 840);
        drawImageSpace(g, 27, 0, 720);
        drawImageSpace(g, 28, 0, 600);
        drawImageSpace(g, 29, 0, 480);
        drawImageSpace(g, 30, 0, 360);
        drawImageSpace(g, 31, 0, 240);
    }

    private void drawImageSpace(Graphics g, int spaceNumber, int x, int y) {
        Image image = new ImageIcon(getClass().getResource("assets/space"+spaceNumber+".jpg")).getImage();
        g.drawImage(image, x, y, CELL_SIZE, CELL_SIZE, this);
    }
}
