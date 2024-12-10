package controller;
import codemodel.*;
import codemodel.assets.*;
import java.util.*;
import view.*;

public class Game {
    private ModelInterface model;
    private CustomControl controlUI;
    private GameUI gameUI;
    private CustomBoard boardUI;
    private ArrayList<Integer> playersInOrder = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();
    private int numPlayers;

    public Game() {
        ArrayList<String> playersNames;
        ArrayList<String> playersColors;
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Integer> spaces = new ArrayList<>();

        MonopolySetup setup = new MonopolySetup();

        numPlayers = setup.getNumberOfPlayers();
        int choice = setup.getChoice();
        playersNames = setup.getPlayerNames();
        playersColors = setup.getPlayerColors();

        gameUI = new GameUI();
        boardUI = gameUI.getBoardPanel();
        controlUI = gameUI.getControlPanel();

        if (choice == 1) { // New Game
            int maxTurns = 100 * numPlayers;
            model = new ModelInterface(numPlayers, playersNames, playersColors, maxTurns, 2);
            boardUI.drawAllPlayer(model.getPlayersColors(), model.getPlayersPositions());
            boardUI.drawAllHouses(model.getNumHouseinAllSpaces());
            setupInitialRoll(players, spaces);
        } else { // Load Game
            model = ModelInterface.loadGame("previous_game.ser");
            playersInOrder = model.getPlayersOrder();
            startGameLoop();
        }
    }

    private void setupInitialRoll(ArrayList<Player> players, ArrayList<Integer> spaces) {
        ArrayList<Integer> results = new ArrayList<>();
        final int[] currentPlayer = {0};

        disableAllButtonsExcept(ButtonPanel.ButtonName.ROLL);

        controlUI.getButtonPanel().setRollDiceListener(e -> {
            if (currentPlayer[0] < numPlayers) {
                Player player = model.getPlayerById(currentPlayer[0]);
                controlUI.updateInfoPlayer(player.getMoney(), player.getProperties().size(), player.getName(), player.getColor());
                ArrayList<Integer> diceResults = model.rollDices();
                int result = model.totalDiceResult(diceResults);

                controlUI.getButtonPanel().updateDiceImages(diceResults.get(0), diceResults.get(1));
                results.add(result);
                currentPlayer[0]++;
                
                if (currentPlayer[0] == numPlayers) {
                    model.definePlayerOrder(results);
                    playersInOrder = model.getPlayersOrder();
                    
                    for (int i = 0; i < numPlayers; i++) {
                        Player p = model.getPlayerById(playersInOrder.get(i));
                        Info inf = new Info(p);
                        players.add(p);
                        colors.add(p.getColor());
                        spaces.add(inf.spaceId);
                    }
                    
                    disableAllButtonsExcept(null);
                    controlUI.getButtonPanel().removeActionListenerFromButton(ButtonPanel.ButtonName.ROLL);
                    startGameLoop();
                }
            }
        });
    }

    private void startGameLoop() {
        setupNextTurn();
    }

    private void setupNextTurn() {
        if (model.isFinished()) {
            endGame();
            return;
        }

        Player player = model.getNextPlayer();
        controlUI.updateInfoPlayer(player.getMoney(), player.getProperties().size(), player.getName(), player.getColor());
        disableAllButtonsExcept(ButtonPanel.ButtonName.ROLL);

        controlUI.getButtonPanel().setRollDiceListener(e -> handlePlayerTurn());
    }

    private void handlePlayerTurn() {
        ArrayList<Integer> diceResults = model.rollDices();
        Info info = model.moveNextPlayer(diceResults);

        controlUI.getButtonPanel().updateDiceImages(diceResults.get(0), diceResults.get(1));
        boardUI.drawAllPlayer(model.getPlayersColors(), model.getPlayersPositions());
        boardUI.drawAllHouses(model.getNumHouseinAllSpaces());
        updateSpaceInfo(info);
        controlUI.updateInfoPlayer(info.player.getMoney(), info.player.getProperties().size(), info.player.getName(), info.player.getColor());

        disableButton(ButtonPanel.ButtonName.ROLL);
        enableButton(ButtonPanel.ButtonName.END);

        setupActionButtons(info);
    }

    private void updateSpaceInfo(Info info) {
        if (info.space instanceof PropertySpace propertySpace) {
            Property property = propertySpace.getProperty();
            controlUI.updateInfoSpace(
                "Property",
                property.getName(),
                property.getPrice(),
                info.spaceId,
                property.getHousePrice(),
                property.getBaseRent(),
                property.getMortgagePrice()
            );
        } else if (info.space instanceof PrisonSpace) {
            controlUI.updateInfoSpace("Jail", "You are in jail", info.spaceId);
        } else if (info.space instanceof CardSpace) {
            controlUI.updateInfoSpace("Card", info.card.getDescription(), info.card.getAmount(), info.spaceId);
        } else if (info.space instanceof MoneySpace){
            controlUI.updateInfoSpace("Money", info.space.getDescription(), info.spaceId);
        } 
        else {
            controlUI.updateInfoSpace(" Empty Space, Sorry", "Why you look description in Empty Space?", info.spaceId);
        }
    }

    private void setupActionButtons(Info info) {
        for (String action : info.possible_actions) {
            switch (action) {
                case "buy" -> enableButton(ButtonPanel.ButtonName.BUY);
                case "sell" -> enableButton(ButtonPanel.ButtonName.SELL);
                case "buyHouse" -> enableButton(ButtonPanel.ButtonName.BUY);
                case "bail" -> enableButton(ButtonPanel.ButtonName.BAIL);
            }
        }

        controlUI.getButtonPanel().setBuyListener(e -> {
            if (info.possible_actions.contains("buy")) {
                model.buyProperty();
            } else if (info.possible_actions.contains("buyHouse")) {
                model.buyHouse();
            } 
            disableButton(ButtonPanel.ButtonName.BUY);
        });

        controlUI.getButtonPanel().setBailListener(e -> {
            if (info.possible_actions.contains("bail")) {
                model.bailFromJail();
            }
            disableButton(ButtonPanel.ButtonName.BAIL);
        });

        controlUI.getButtonPanel().setSellListener(e -> {
            if (info.possible_actions.contains("sell")) {
                model.sellProperty();
            }
            disableButton(ButtonPanel.ButtonName.SELL);
        });

        controlUI.getButtonPanel().setEndTurnListener(e -> {
            model.goToNextPlayer();
            controlUI.getButtonPanel().removeActionListenerFromButton(ButtonPanel.ButtonName.ROLL);
            controlUI.getButtonPanel().removeActionListenerFromButton(ButtonPanel.ButtonName.BUY);
            controlUI.getButtonPanel().removeActionListenerFromButton(ButtonPanel.ButtonName.SELL);
            controlUI.getButtonPanel().removeActionListenerFromButton(ButtonPanel.ButtonName.BAIL);
            controlUI.getButtonPanel().removeActionListenerFromButton(ButtonPanel.ButtonName.END);
            model.saveGame("previous_game.ser");
            setupNextTurn();
        });
    }

    private void disableAllButtonsExcept(ButtonPanel.ButtonName buttonToEnable) {
        for (ButtonPanel.ButtonName button : ButtonPanel.ButtonName.values()) {
            controlUI.getButtonPanel().setButtonEnabled(button, button == buttonToEnable);
        }
    }

    private void enableButton(ButtonPanel.ButtonName button) {
        controlUI.getButtonPanel().setButtonEnabled(button, true);
    }

    private void disableButton(ButtonPanel.ButtonName button) {
        controlUI.getButtonPanel().setButtonEnabled(button, false);
    }

    private void endGame() {
        System.out.println("Game over!");
        gameUI.dispose();
        System.exit(0);
    }
}
