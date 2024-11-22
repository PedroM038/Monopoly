//package model;

import assets.*;
import java.util.ArrayList;


public class BackEndInterface {
    private ArrayList<Player> players;
    private Board board;
    private Deck deck;
    private Dice dices[2];
    private Bank banker;
    private JailGuard guard;
    private int maxTurns;
    private ArrayList<Integer>playerOrder;

    public BackEndInterface(int numPlayers, ArrayList<String> playersName, ArrayList<String> playersColors, int maxTurns) {
        this.players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(playersName.get(i), playersColors.get(i)), GameConstants.PLAYER_INITIAL_MONEY);
        }
        this.board = new Board(?????);
        this.deck = new Deck();
        deck.loadCardsFromFile("../../assets/deck/cards.txt");
        this.dices = new Dice[2];
        this.dices[0] = new Dice(6);
        this.dices[1] = new Dice(6);

        this.banker = new Bank();
        this.guard = new JailGuard(numPlayers, GameConstants.JAIL_BAIL_VALUE);

        this.maxTurns = maxTurns;

        this.playerOrder = new ArrayList<Integer>();
    }
}