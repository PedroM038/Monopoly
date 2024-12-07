package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class CustomControl extends JPanel {

    private final PlayerPanel playerPanel;
    private final SpacePanel spacePanel;
    private final ButtonPanel buttonPanel;

    public CustomControl() {
        setPreferredSize(new Dimension(840, 1080));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        playerPanel = new PlayerPanel();
        playerPanel.setPreferredSize(new Dimension(840, 400));
        add(playerPanel, BorderLayout.NORTH);

        spacePanel = new SpacePanel();
        spacePanel.setPreferredSize(new Dimension(840, 400));
        spacePanel.setBackground(Color.GRAY);
        add(spacePanel, BorderLayout.CENTER);

        buttonPanel = new ButtonPanel();
        buttonPanel.setPreferredSize(new Dimension(840, 280));
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateInfoPlayer(int money, int numProperties, String name, String playerColor) {
        this.playerPanel.updatePlayerInfo(money, numProperties, name, playerColor);
    }

    public void updateInfoSpace(String name, String description, int price, int numSpace, int housePrice, int mortgagePrice, int rent) {
        this.spacePanel.updateSpaceInfo(name, description, price, numSpace, housePrice, mortgagePrice, rent);
    }
    
    public void updateInfoSpace(String name, String description, int price, int numSpace) {
        this.spacePanel.updateSpaceInfo(name, description, price, numSpace);
    }

    public void updateInfoSpace(String name, String description, int numSpace) {
        this.spacePanel.updateSpaceInfo(name, description, numSpace);
    }

    public void updateDiceImg(int dice1, int dice2) {
        this.buttonPanel.updateDiceImages(dice1, dice2);
    }

    public PlayerPanel getPlayerPanel() {
        return this.playerPanel;
    }

    public SpacePanel getSpacePanel() {
        return this.spacePanel;
    }

    public ButtonPanel getButtonPanel() {
        return this.buttonPanel;
    }
}