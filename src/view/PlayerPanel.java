package view;

import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel{

    private final JLabel playerTurnLabel;
    private final JLabel moneyLabel;
    private final JLabel numPropertiesLabel;
    private final JLabel nameLabel;
    private final JLabel playerImage;
    private final JPanel infoPanel;
    private final JPanel headerPanel;
    
    public PlayerPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Create header panel to hold both image and title
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        // Create player image label
        playerImage = new JLabel();
        playerImage.setPreferredSize(new Dimension(80, 80));
        playerImage.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(playerImage, BorderLayout.WEST); 
        
        // Create title label
        playerTurnLabel = new JLabel("PLAYER TURN");
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerTurnLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(playerTurnLabel, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
    
        // Create center info panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        nameLabel = new JLabel("");
        moneyLabel = new JLabel("");
        numPropertiesLabel = new JLabel("");
        
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        moneyLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        numPropertiesLabel.setFont(new Font("Verdana", Font.PLAIN, 18));

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numPropertiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        infoPanel.add(nameLabel);
        infoPanel.add(moneyLabel);
        infoPanel.add(numPropertiesLabel);
        
        add(infoPanel, BorderLayout.CENTER);
    }
    
    public void updatePlayerInfo(int money, int numProperties, String name, String playerColor) {
        try {
            if(getClass().getResource("assets/"+playerColor+".png") != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource("assets/"+playerColor+".png"));
                Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                this.playerImage.setIcon(new ImageIcon(img));
            }
            else {
                ImageIcon icon = new ImageIcon(getClass().getResource("assets/white.png"));
                Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                this.playerImage.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            System.err.println("Error loading player image: " + e.getMessage());
        }
        
        nameLabel.setText("Name = " + name);
        moneyLabel.setText("Money = " + String.valueOf(money));
        numPropertiesLabel.setText("Number Properties = " + String.valueOf(numProperties));
        infoPanel.revalidate();
        infoPanel.repaint();
        headerPanel.revalidate();
        headerPanel.repaint();
    }
}
