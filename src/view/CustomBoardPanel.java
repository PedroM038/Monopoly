package view;

import java.awt.*;
import javax.swing.*;

public class CustomBoardPanel extends JPanel {
    final private Image boardImage;

    public CustomBoardPanel() {
        boardImage = new ImageIcon(getClass().getResource("./assets/background.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 150, 200, this);
    }
}
