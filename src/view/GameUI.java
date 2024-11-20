package view;

import assets.Dice;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
public class GameUI extends JFrame {
    private JPanel boardPanel;
    private JPanel controlPanel;
    private JButton rollButton;
    private JLabel diceResultLabel;
    private final Dice dice = new Dice(6);

    public GameUI() {
        super("Monopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLayout(new BorderLayout());
        
        initBoardPanel();
        initControlPanel();
        
        setVisible(true);
    }

    private void initBoardPanel() {
        boardPanel = new JPanel();
        add(boardPanel, BorderLayout.CENTER); // Central panel for the board
    }
    
    private void initControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout()); // Layout para organizar botões
        add(controlPanel, BorderLayout.SOUTH); // Bottom panel for controls
        
        rollButton = new JButton("Roll");
        controlPanel.add(rollButton);

        diceResultLabel = new JLabel("Dice result: ");
        controlPanel.add(diceResultLabel);

        //adiciona funcionalidade ao botão
        rollButton.addActionListener(e -> {
            int result = dice.roll();
            diceResultLabel.setText("Dice result: " + result);
        });
    }
}