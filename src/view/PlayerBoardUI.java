
package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PlayerBoardUI extends JPanel {
    private ArrayList<String> colors;
    private ArrayList<Integer> positions;
    private static final int PLAYER_SIZE = 40;
    private final SpaceBoardUI spaceBoard;

    public PlayerBoardUI(SpaceBoardUI spaceBoard) {
        this.spaceBoard = spaceBoard;
        setOpaque(false);
        colors = new ArrayList<>();
        positions = new ArrayList<>();
        setBounds(0, 0, 1080, 1080);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAllPlayers(g);
    }

    public void drawAllPlayers(ArrayList<String> playerColors, ArrayList<Integer> playerPositions) {
        this.colors = playerColors;
        this.positions = playerPositions;
        repaint();
    }

    private void drawAllPlayers(Graphics g) {
        int numPlayers = colors.size();
        for (int i = 0; i < numPlayers; i++) {
            drawPlayer(g, colors.get(i), positions.get(i), i, numPlayers);
        }
    }

    private void drawPlayer(Graphics g, String color, int position, int playerIndex, int totalPlayers) {
        int x = spaceBoard.getXSpace(position);
        int y = spaceBoard.getYSpace(position);

        x += 40;
        y += 40;
        g.setColor(getColorFromString(color));
        g.fillOval(x, y, PLAYER_SIZE, PLAYER_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, PLAYER_SIZE, PLAYER_SIZE);
    }

    private Color getColorFromString(String color) {
        return switch (color.toLowerCase()) {
            case "blue" -> Color.BLUE;
            case "yellow" -> Color.YELLOW;
            case "white" -> Color.WHITE;
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            default -> Color.GRAY;
        };
    }
}