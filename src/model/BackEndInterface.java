//package model;

import assets.*;
import java.util.ArrayList;


public class BackEndInterface {
    private ArrayList<Player> players;
    private Board board;
    private Deck deck;
    private Dice dice;
    private JailGuard guard;
    private int maxTurns;
    private ArrayList<Integer>playerOrder;

    private int nextPlayerId;

    public BackEndInterface(int numPlayers, ArrayList<String> playersName, ArrayList<String> playersColors, int maxTurns) {
        this.players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(playersName.get(i), playersColors.get(i), GameConstants.PLAYER_INITIAL_MONEY));
        }
        this.board = new Board(?????);
        this.deck = new Deck();
        deck.loadCardsFromFile("../../assets/deck/cards.txt");
        this.dice = new Dice(6);

        this.guard = new JailGuard(numPlayers, GameConstants.JAIL_BAIL_VALUE);

        this.maxTurns = maxTurns;

        this.playerOrder = new ArrayList<Integer>();
    }

    public ArrayList<Integer> rollDices() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(Integer.valueOf(this.dice.roll()));
        result.add(Integer.valueOf(this.dice.roll()));
        return result;
    }

    public int totalDiceResult(ArrayList<Integer> diceResults)

    public void definePlayerOrder(ArrayList<Integer>playersResults) {
        return;
    }

    public Info moveNextPlayer(ArrayList<Integer> diceResults) {
        return null;
    }

    public void buyProperty() {
        return;
    }

    public void buyHouse() {
        return;
    }

    public void sellProperty() {
        return;
    }

    public void bailFromJail() {
        return;
    }

    public void goToNext() {
        return;
    }

    public ArrayList<Player> endGame() {
        return null;
    }

    public boolean isFinished() {
        return false;
    }

    public ArrayList<Player> getPodium() {
        return null;
    }




}