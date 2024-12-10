package view;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonPanel extends JPanel {

    private final JButton buyButton, sellButton, rollDiceButton, endTurnButton, bailButton;
    private final JLabel dice1Label, dice2Label;
    
    public ButtonPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buyButton = new JButton("Buy");
        buyButton.setBackground(Color.GREEN);
        sellButton = new JButton("Sell");
        sellButton.setBackground(Color.RED);
        rollDiceButton = new JButton("Roll Dice");
        endTurnButton = new JButton("End Turn");
        bailButton = new JButton("Bail");
        bailButton.setBackground(Color.MAGENTA);

        buyButton.setActionCommand("BUY");
        sellButton.setActionCommand("SELL");
        rollDiceButton.setActionCommand("ROLL");
        endTurnButton.setActionCommand("END");
        bailButton.setActionCommand("BAIL");

        dice1Label = new JLabel();
        dice2Label = new JLabel();
        dice1Label.setPreferredSize(new Dimension(100, 100));
        dice2Label.setPreferredSize(new Dimension(100, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.5; 
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.CENTER; 
        add(dice1Label, gbc);

        gbc.gridx = 1; 
        add(dice2Label, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.ipadx = 10; 
        gbc.ipady = 10; 
        add(rollDiceButton, gbc);

        gbc.gridy = 1;
        add(endTurnButton, gbc);

        gbc.gridx = 3; gbc.gridy = 0;
        add(buyButton, gbc);

        gbc.gridy = 1;
        add(sellButton, gbc);

        gbc.gridx = 3; gbc.gridy = 2;
        add(bailButton, gbc);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("assets/dice" + 6 + ".png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("assets/dice" + 6 + ".png"));
        Image img1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Image img2 = icon2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.dice1Label.setIcon(new ImageIcon(img1));
        this.dice2Label.setIcon(new ImageIcon(img2));
    }
    
    public void setBuyListener(ActionListener listener) {
        buyButton.addActionListener(listener);
    }
    
    public void setSellListener(ActionListener listener) {
        sellButton.addActionListener(listener);
    }
    
    public void setRollDiceListener(ActionListener listener) {
        rollDiceButton.addActionListener(listener);
    }
    
    public void setEndTurnListener(ActionListener listener) {
        endTurnButton.addActionListener(listener);
    }

    public void setBailListener(ActionListener listener) {
        bailButton.addActionListener(listener);
    }
    
    public void updateDiceImages(int dice1Value, int dice2Value) {
        ImageIcon icon1 = new ImageIcon(getClass().getResource("assets/dice" + dice1Value + ".png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("assets/dice" + dice2Value + ".png"));
        Image img1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Image img2 = icon2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.dice1Label.setIcon(new ImageIcon(img1));
        this.dice2Label.setIcon(new ImageIcon(img2));
        repaint();
    }

    public enum ButtonName {
        BUY, SELL, ROLL, END, BAIL
    }

    public void setButtonEnabled(ButtonName buttonName, boolean enabled) {
        switch (buttonName) {
            case BUY -> buyButton.setEnabled(enabled);
            case SELL -> sellButton.setEnabled(enabled);
            case ROLL -> rollDiceButton.setEnabled(enabled);
            case END -> endTurnButton.setEnabled(enabled);
            case BAIL -> bailButton.setEnabled(enabled);
        }
    }

    public void removeActionListenerFromButton(ButtonName button) {
        JButton btn = getButton(button);
        for (ActionListener al : btn.getActionListeners()) {
            btn.removeActionListener(al);
        }
    }

    private JButton getButton(ButtonName buttonName) {
        return switch (buttonName) {
            case BUY -> buyButton;
            case SELL -> sellButton;
            case ROLL -> rollDiceButton;
            case END -> endTurnButton;
            case BAIL -> bailButton;
        };
    }

    public JButton getRollDiceButton() {
        return rollDiceButton;
    }
}
