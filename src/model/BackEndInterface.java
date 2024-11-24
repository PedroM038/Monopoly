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
    private int numTurns;
    private ArrayList<Integer>playersOrder;

    private int nextPlayerPos;

    public BackEndInterface(int numPlayers, ArrayList<String> playersName, ArrayList<String> playersColors, int maxTurns) {
        this.players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(playersName.get(i), i, playersColors.get(i), GameConstants.PLAYER_INITIAL_MONEY));
        }
        this.board = new Board(GameConstants.BOARD_SIZE, numPlayers, GameConstants.PRISON_ID, 
            GameConstants.PROPERTIES_ID, GameConstants.CARDS_ID, GameConstants.MONEY_ID);
        this.deck = new Deck();
        deck.loadCardsFromFile("../../assets/deck/cards.txt");
        this.dice = new Dice(6);

        this.guard = new JailGuard(numPlayers, GameConstants.JAIL_BAIL_VALUE);

        this.maxTurns = maxTurns;
        this.numTurns = 0;

        this.playersOrder = new ArrayList<Integer>();
        this.nextPlayerPos = 0;
    }

    public ArrayList<Integer> rollDices() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(Integer.valueOf(this.dice.roll()));
        result.add(Integer.valueOf(this.dice.roll()));
        return result;
    }

    public int totalDiceResult(ArrayList<Integer> diceResults) {
        int total = 0;
        for (int i = 0; i < diceResults.size(); i++) {
            total += diceResults.get(i);
        }
        return total;
    }

    public void definePlayerOrder(ArrayList<Integer>playersResults) {
        ArrayList<Integer[]>pairsResultPlayer = new ArrayList<>();
        for (int i = 0; i < playersResults.size(); i++) {
            pairsResultPlayer.add(new Integer[]{playersResults.get(i), i});
        }
        pairsResultPlayer.sort((array1, array2) -> Integer.compare(array1[0], array2[0]));
        for (int i = playersResults.size(); i >= 0; i--) {
            this.playersOrder.add(pairsResultPlayer.get(i)[1]);
        }
    }

    public Info moveNextPlayer(ArrayList<Integer> diceResults) {
        Player player = this.getNextPlayer();
        if (this.isEliminated(player)) {
            return null;
        }

        this.numTurns += 1;
        Info info = new Info(player);
        if (player.isLocked()) {
            info.space = this.getPlayerSpace(player);
            if (this.guard.freePlayerWithDice(player, diceResults.get(0), diceResults.get(1))) {
                return info;
            }
            this.guard.reducePenalty(player);
            if (this.guard.canBail(player)) {
                info.possible_actions.add("bail");
            }
            return info;
        }
        int numMoves = this.totalDiceResult(diceResults);
        if (this.board.goesThroughStart(player, numMoves)) {
            info.gotThroughStart = true;
        }
        this.board.movePlayer(player, numMoves);
        Space space = this.getPlayerSpace(player);
        PlayerSpaceHandler.solve(player, space, info);
        return info;
    }

    public void goToNextPlayer() {
        this.nextPlayerPos = (this.nextPlayerPos + 1) % this.players.size();
    }

    public Space getPlayerSpace(Player player) {
        return this.board.getPlayerSpace(player);
    }

    // player in the present turn buys property he is in
    public void buyProperty() {
        Player player = this.getNextPlayer();
        PropertySpace space = (PropertySpace) this.getPlayerSpace(player);
        Property property = space.getProperty();
        Bank.buyProperty(player, property);
        return;
    }

    // player in the present turn buys house of property he is in
    public void buyHouse() {
        Player player = this.getNextPlayer();
        PropertySpace space = (PropertySpace) this.getPlayerSpace(player);
        Property property = space.getProperty();
        Bank.buyHouse(player, property);
        return;
    }

    // player in present turn sells desired property
    public void sellProperty(Property property) {
        Player player = this.getNextPlayer();
        Bank.sellProperty(player, property);
    }

    // player in present turn bails from jail
    public void bailFromJail() {
        Player player = this.getNextPlayer();
        this.guard.bailPlayer(player);
    }

    public boolean isFinished() {
        return this.maxTurns == this.numTurns;
    }

    public ArrayList<Player> getPodium() {
        ArrayList<Integer[]>pairsResultPlayer = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            pairsResultPlayer.add(new Integer[]{player.getTotalAssets(), i});
        }
        pairsResultPlayer.sort((array1, array2) -> Integer.compare(array1[0], array2[0]));
        ArrayList<Player> podium = new ArrayList<>();
        for (int i = this.players.size(); i >= 0; i--) {
            int pos = pairsResultPlayer.get(i)[1];
            podium.add(this.players.get(pos));
        }
        return podium;
    }

    public boolean isEliminated(Player player) {
        return player.getTotalAssets() <= 0;
    }


    // GETTERS
    public ArrayList<Player> getPlayers() {
        return this.players;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public Deck getDeck() {
        return this.deck;
    }
    
    public Dice getDice() {
        return this.dice;
    }
    
    public JailGuard getGuard() {
        return this.guard;
    }
    
    public int getMaxTurns() {
        return this.maxTurns;
    }
    
    public ArrayList<Integer> getPlayersOrder() {
        return this.playersOrder;
    }

    public Player getNextPlayer() {
        return this.players.get(this.playersOrder.get(this.nextPlayerPos));
    }

}