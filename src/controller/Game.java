package controller;

import codemodel.*;
import java.util.*;
import view.*;

public class Game {
    public Game() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> playersNames = new ArrayList<>();
        ArrayList<String> playersColors = new ArrayList<>();
        ArrayList<Integer> houses = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            houses.add(0);
        }
        int numPlayers;
        int choice;
        int maxTurns;
        int numDice = 2;
        ModelInterface model;
        MonopolySetup setup = new MonopolySetup();

        numPlayers = setup.getNumberOfPlayers();
        choice = setup.getChoice();
        playersNames = setup.getPlayerNames();
        playersColors = setup.getPlayerColors();

        GameUI gameUI = new GameUI();
        CustomBoard boardUI = gameUI.getBoardPanel();
        CustomControl controlUI = gameUI.getControlPanel();
        boardUI.drawAllHouses(houses);

        // choice == 1 new game
        // choice == 2 load game
        if (choice == 1) {
            maxTurns = 30 * numPlayers;
            model = new ModelInterface(numPlayers, playersNames, playersColors, maxTurns, numDice);
        
            // Define player order through dice rolling
            ArrayList<Integer> results = new ArrayList<>();
            final int[] currentPlayer = {0}; // Track current player rolling dice
            
            // Enable only roll button initially
            controlUI.getButtonPanel().setButtonEnabled(ButtonPanel.ButtonName.BUY, false);
            controlUI.getButtonPanel().setButtonEnabled(ButtonPanel.ButtonName.SELL, false);
            controlUI.getButtonPanel().setButtonEnabled(ButtonPanel.ButtonName.END, false);
            controlUI.getButtonPanel().setButtonEnabled(ButtonPanel.ButtonName.ROLL, true);
        
            // Add roll dice listener
            controlUI.getButtonPanel().setRollDiceListener(e -> {
                if (currentPlayer[0] < numPlayers) {
                    // Roll dice and get results
                    ArrayList<Integer> diceResults = model.rollDices();
                    int result = model.totalDiceResult(diceResults);
                    
                    // Update dice images in UI
                    controlUI.getButtonPanel().updateDiceImages(diceResults.get(0), diceResults.get(1));
                    
                    // Store result and update player info
                    results.add(result);                    
                    currentPlayer[0]++;
                    
                    // When all players have rolled
                    if (currentPlayer[0] == numPlayers) {
                        // Define final player order
                        model.definePlayerOrder(results);
                        ArrayList<Integer> playersInOrder = model.getPlayersOrder();
                        
                        // Disable roll button after all players have rolled
                        controlUI.getButtonPanel().setButtonEnabled(ButtonPanel.ButtonName.ROLL, false);
                        
                        // Print final order
                        for (int i = 0; i < numPlayers; i++) {
                            Player player = model.getPlayerById(playersInOrder.get(i));
                            System.out.println("Order " + (i+1) + ": " + player.getName());
                        }
                    }
                }
            });
        }
        
        else {
            model = ModelInterface.loadGame("previous_game.ser");
        }
    }
}
