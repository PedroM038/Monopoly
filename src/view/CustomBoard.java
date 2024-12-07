package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CustomBoard extends JPanel {
    private final SpaceBoardUI spaceBoardUI;
    private final PlayerBoardUI playerBoardUI;

    public CustomBoard() {
        setPreferredSize(new Dimension(1080, 1080));
        setLayout(null);
        spaceBoardUI = new SpaceBoardUI();
        spaceBoardUI.setBounds(0, 0, 1080, 1080);
        playerBoardUI = new PlayerBoardUI(spaceBoardUI);
        playerBoardUI.setBounds(0, 0, 1080, 1080);
        add(playerBoardUI);
        add(spaceBoardUI);
        setComponentZOrder(spaceBoardUI, 1);
        setComponentZOrder(playerBoardUI, 0);
    }

    public void drawAllPlayer(ArrayList<String> playerColors, ArrayList<Integer> playerPositions) {
        playerBoardUI.drawAllPlayers(playerColors, playerPositions);
        revalidate();
        repaint();
    }

    public void drawAllHouses(ArrayList<Integer> houses) {
        spaceBoardUI.drawAllHouses(houses);
        revalidate();
        repaint();
    }
}
