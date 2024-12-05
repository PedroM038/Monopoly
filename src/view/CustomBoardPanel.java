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
        drawAllImageSpace(g);
        drawGridLines(g);
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

    private int getXSpace(int space) {
        if (space < SPACES_TOP) {
            return (space * CELL_SIZE);
        } else if (space < SPACES_TOP + SPACES_RIGHT) {
            return BOARD_SIZE - BOARD_MARGIN;
        } else if (space < SPACES_TOP + SPACES_RIGHT + SPACES_BOTTOM) {
            return BOARD_SIZE - 240 - ((space - SPACES_TOP - SPACES_RIGHT) * CELL_SIZE);
        } else {
            return 0;
        }
    }

    private int getYSpace(int space) {
        if (space < SPACES_TOP) {
            return 0;
        } else if (space < SPACES_TOP + SPACES_RIGHT) {
            return BOARD_MARGIN + ((space - SPACES_TOP) * CELL_SIZE);
        } else if (space < SPACES_TOP + SPACES_RIGHT + SPACES_BOTTOM) {
            return BOARD_SIZE - BOARD_MARGIN;
        } else {
            return BOARD_SIZE - 240 - ((space - SPACES_TOP - SPACES_RIGHT - SPACES_BOTTOM) * CELL_SIZE);
        }
    }

    private void drawAllImageSpace(Graphics g) {
        for (int i = 0; i < 32; i++) {
            int x = getXSpace(i);
            int y = getYSpace(i);
            drawImageSpace(g, i, x, y);
        }
    }

    private void drawImageSpace(Graphics g, int spaceNumber, int x, int y) {
        Image image = new ImageIcon(getClass().getResource("assets/space"+spaceNumber+".jpg")).getImage();
        g.drawImage(image, x, y, CELL_SIZE, CELL_SIZE, this);
    }
}
