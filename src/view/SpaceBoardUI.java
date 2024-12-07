package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SpaceBoardUI extends JPanel{
    private ArrayList<Integer> houses;
    public SpaceBoardUI() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        try{
            Image background = new ImageIcon(getClass().getResource("assets/background.jpg")).getImage();
            g.drawImage(background, 120, 120, 840, 840, this);
        } catch (Exception e) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(120, 120, 840, 840);
            g.setColor(Color.BLACK);
            g.drawRect(120, 120, 840, 840);
        }

        for (int i = 0; i < 32; i++) {
            try {
                Image spaceImage = new ImageIcon(getClass().getResource("assets/space" + i + ".jpg")).getImage();
                g.drawImage(spaceImage, getXSpace(i), getYSpace(i), ViewConstants.CELL_SIZE, ViewConstants.CELL_SIZE, this);
            } catch (Exception e) {
                // If image not found, draw a rectangle placeholder
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(getXSpace(i), getYSpace(i), ViewConstants.CELL_SIZE, ViewConstants.CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(getXSpace(i), getYSpace(i), ViewConstants.CELL_SIZE, ViewConstants.CELL_SIZE);
            }
        }

        drawAllHouses(g);
    }

    public int getXSpace(int space) {
        if (space < ViewConstants.SPACES_TOP) {
            return (space * ViewConstants.CELL_SIZE);
        } else if (space < ViewConstants.SPACES_TOP + ViewConstants.SPACES_RIGHT) {
            return ViewConstants.BOARD_SIZE - ViewConstants.BOARD_MARGIN;
        } else if (space < ViewConstants.SPACES_TOP + ViewConstants.SPACES_RIGHT + ViewConstants.SPACES_BOTTOM) {
            return ViewConstants.BOARD_SIZE - 240 - ((space - ViewConstants.SPACES_TOP - ViewConstants.SPACES_RIGHT) * ViewConstants.CELL_SIZE);
        } else {
            return 0;
        }
    }

    public int getYSpace(int space) {
        if (space < ViewConstants.SPACES_TOP) {
            return 0;
        } else if (space < ViewConstants.SPACES_TOP + ViewConstants.SPACES_RIGHT) {
            return ViewConstants.BOARD_MARGIN + ((space - ViewConstants.SPACES_TOP) * ViewConstants.CELL_SIZE);
        } else if (space < ViewConstants.SPACES_TOP + ViewConstants.SPACES_RIGHT + ViewConstants.SPACES_BOTTOM) {
            return ViewConstants.BOARD_SIZE - ViewConstants.BOARD_MARGIN;
        } else {
            return ViewConstants.BOARD_SIZE - 240 - ((space - ViewConstants.SPACES_TOP - ViewConstants.SPACES_RIGHT - ViewConstants.SPACES_BOTTOM) * ViewConstants.CELL_SIZE);
        }
    }

    public void drawAllHouses(ArrayList<Integer> houses) {
        this.houses = houses;
        repaint();
    }

    private void drawAllHouses(Graphics g){
        int numSpaces = houses.size();
        for (int i = 0; i < numSpaces; i++){
            int numHouses = houses.get(i);
            drawHouse(g, i, numHouses);
        }
    }

    private void drawHouse(Graphics g, int numSpace, int numHouses){
        int x = getXSpace(numSpace);
        int y = getYSpace(numSpace);       
        g.setColor(Color.BLACK);
        if (numHouses >= 1) {
            g.fillRect(x + 50, y, 20, 20); // House 1
            g.drawRect(x + 50, y, 20, 20);
        }
        if (numHouses >= 2) {
            g.fillRect(x + 100, y + 50, 20, 20); // House 2 
            g.drawRect(x + 100, y + 50, 20, 20);
        }
        if (numHouses >= 3) {
            g.fillRect(x + 50, y + 100, 20, 20); // House 3
            g.drawRect(x + 50, y + 100, 20, 20);
        }
        if (numHouses >= 4) {
            g.fillRect(x, y + 50, 20, 20); // House 4
            g.drawRect(x, y + 50, 20, 20);
        }
    }
}