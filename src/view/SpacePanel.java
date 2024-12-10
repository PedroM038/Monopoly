package view;
import java.awt.*;
import javax.swing.*;

public class SpacePanel extends JPanel {
    private final JLabel priceLabel;
    private final JLabel descriptionLabel;
    private final JLabel spaceTitleLabel;
    private final JLabel nameSpaceLabel;
    private final JLabel spaceImage;
    private final JPanel infoPanel;
    private final JPanel headerPanel;
    private final JLabel housePrice;
    private final JLabel mortgagePrice;
    private final JLabel rent;
    
    public SpacePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Header panel setup remains the same
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        spaceImage = new JLabel();
        spaceImage.setPreferredSize(new Dimension(80, 80));
        spaceImage.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(spaceImage, BorderLayout.WEST);
        
        spaceTitleLabel = new JLabel("SPACE INFO");
        spaceTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        spaceTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(spaceTitleLabel, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
    
        // Modified info panel with more labels
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(9, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        nameSpaceLabel = new JLabel("");
        descriptionLabel = new JLabel("");
        priceLabel = new JLabel("");
        housePrice = new JLabel("");
        mortgagePrice = new JLabel("");
        rent = new JLabel("");
        
        // Set font for all labels
        Font labelFont = new Font("Verdana", Font.PLAIN, 16);
        JLabel[] labels = {nameSpaceLabel, descriptionLabel, priceLabel, housePrice,
                          mortgagePrice, rent};
        
        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(label);
        }
        
        add(infoPanel, BorderLayout.CENTER);
    }
    
    public void updateSpaceInfo(String name, String description, int price, int numSpace, int priceHouse, int rentPrice, int mortgage) {
        resetInfoPanel(); 
        updateSpaceImage(numSpace);
        nameSpaceLabel.setText("Name: " + name);
        descriptionLabel.setText("Description: " + description);
        priceLabel.setText("PRICE: $" + price);
        housePrice.setText("HOUSE PRICE: $" + priceHouse);
        mortgagePrice.setText("MORTGAGE: $" + mortgage);
    
        infoPanel.add(nameSpaceLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(housePrice);
        infoPanel.add(mortgagePrice);
    
        refreshPanels();
    }
    
    public void updateSpaceInfo(String name, String description, int price, int numSpace) {
        resetInfoPanel();
        updateSpaceImage(numSpace);
    
        nameSpaceLabel.setText("Name: " + name);
        descriptionLabel.setText("Description: " + description);
        priceLabel.setText("PRICE: $" + price);
    
        infoPanel.add(nameSpaceLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(priceLabel);
    
        refreshPanels();
    }
    
    public void updateSpaceInfo(String name, String description, int numSpace) {
        resetInfoPanel();
        updateSpaceImage(numSpace);
    
        nameSpaceLabel.setText("Name: " + name);
        descriptionLabel.setText("Description: " + description);
    
        infoPanel.add(nameSpaceLabel);
        infoPanel.add(descriptionLabel);
    
        refreshPanels();
    }
    
    private void resetInfoPanel() {
        infoPanel.removeAll(); 
    }
    
    private void refreshPanels() {
        infoPanel.revalidate();
        infoPanel.repaint();
        headerPanel.revalidate();
        headerPanel.repaint();
    }
    
    private void updateSpaceImage(int numSpace) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("assets/space" + numSpace + ".jpg"));
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            spaceImage.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("Error loading space image: " + e.getMessage());
        }
    }    
}