/*
 * The ModelInterface class represents the interface of the model
 * The controller only needs to communicate with this class
 * It stores all data necessary to play the game, and handles all actions done by the players
 */

package codemodel;

import codemodel.assets.*;

import java.util.ArrayList;
import java.io.*;


public class ModelInterface implements Serializable {
    private ArrayList<Player> players;
    private Board board;
    private Deck deck;
    private Dice dice;
    private Integer numDice;
    private JailGuard guard;
    private Integer maxTurns;
    private Integer numTurns;
    private ArrayList<Integer>playersOrder = new ArrayList<Integer>();
    private int nextPlayerPos;

    public ModelInterface(int numPlayers, ArrayList<String> playersName, ArrayList<String> playersColors, int maxTurns, int numDice) {
        this.players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(playersName.get(i), i, playersColors.get(i), GameConstants.PLAYER_INITIAL_MONEY));
        }
        this.board = new Board(GameConstants.BOARD_SIZE, numPlayers, GameConstants.PRISON_ID, 
            GameConstants.PROPERTIES_ID, GameConstants.CARDS_ID, GameConstants.MONEY_ID);
        this.deck = new Deck();
        deck.loadCardsFromFile("assets/deck/cards.txt");
        this.dice = new Dice(6);
        this.numDice = numDice;

        this.guard = new JailGuard(numPlayers, GameConstants.JAIL_BAIL_VALUE);

        this.maxTurns = maxTurns;
        this.numTurns = 0;

        this.playersOrder = new ArrayList<Integer>();
    }

    public ArrayList<Integer> rollDices() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < this.numDice; i++) {
            result.add(Integer.valueOf(this.dice.roll()));
        }
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
        for (int i = playersResults.size()-1; i >= 0; i--) {
            this.playersOrder.add(pairsResultPlayer.get(i)[1]);
        }
    }

    public Info moveNextPlayer(ArrayList<Integer> diceResults) {
        Player player = this.getNextPlayer();
        if (this.isEliminated(player)) {
            return null;
        }

        this.numTurns += 1;
        Info info;
        if (player.isLocked()) {
            info = new Info(player);
            info.space = this.getPlayerSpace(player);
            info.spaceId = this.board.getPlayerPos(player);
            if (this.guard.freePlayerWithDice(player, diceResults.get(0), diceResults.get(1))) {
                info.possible_actions.add("bail");
            }
            else if (this.guard.canBail(player)) {
                info.possible_actions.add("bail");
            }
            this.guard.reducePenalty(player);
            return info;
        }
        int numMoves = this.totalDiceResult(diceResults);
        this.board.movePlayer(player, numMoves);
        Space space = this.getPlayerSpace(player);
        int spaceId = this.board.getPlayerPos(player);
        info = PlayerSpaceHandler.solve(player, space, spaceId, this);
        if (this.board.goesThroughStart(player, numMoves)) {
            info.gotThroughStart = true;
        }
        info.numMoves = numMoves;
        return info;
    }

    public void goToNextPlayer() {
        this.nextPlayerPos = (this.nextPlayerPos + 1) % this.players.size();
        while (this.isEliminated(this.getNextPlayer())) {
            this.nextPlayerPos = (this.nextPlayerPos + 1) % this.players.size();
        }
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

    // player in present turn sells the property he is in
    public void sellProperty() {
        Player player = this.getNextPlayer();
        PropertySpace space = (PropertySpace) this.getPlayerSpace(player);
        Property property = space.getProperty();
        Bank.sellProperty(player, property);
    }

    // player in present turn bails from jail
    public void bailFromJail() {
        Player player = this.getNextPlayer();
        this.guard.bailPlayer(player);
    }

    // game is finished when all turns have ended or only one player left
    public boolean isFinished() {
        int leftPlayers = 0;
        for (int i = 0; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            if (!player.isEliminated()) {
                leftPlayers++;
            }
        }
        return this.maxTurns == this.numTurns || leftPlayers == 1;
    }

    public ArrayList<Player> getPodium() {
        ArrayList<Integer[]>pairsResultPlayer = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            pairsResultPlayer.add(new Integer[]{player.getTotalAssets(), i});
        }
        pairsResultPlayer.sort((array1, array2) -> Integer.compare(array1[0], array2[0]));
        ArrayList<Player> podium = new ArrayList<>();
        for (int i = this.players.size()-1; i >= 0; i--) {
            int pos = pairsResultPlayer.get(i)[1];
            if (!this.isEliminated(this.players.get(pos))) {
                podium.add(this.players.get(pos));
            }
        }
        return podium;
    }

    public Player getWinner() {
        return this.getPodium().get(0);
    }

    public boolean isEliminated(Player player) {
        return player.isEliminated();
    }

    public ArrayList<Property> getProperties() {
        return this.board.getProperties();
    }


    // SAVE/LOAD games
    public void saveGame(String filename) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); 
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static ModelInterface loadGame (String filename) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            ModelInterface restored = (ModelInterface) objectInputStream.readObject();
            objectInputStream.close();
            return restored;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

    public int getNumTurns() {
        return this.numTurns;
    }

    public int getLeftTurns() {
        return this.maxTurns - this.numTurns;
    }
    
    public ArrayList<Integer> getPlayersOrder() {
        return this.playersOrder;
    }

    public Player getPlayerById(int id) {
        return this.players.get(id);
    }

    public Player getNextPlayer() {
        return this.players.get(this.playersOrder.get(this.nextPlayerPos));
    }
    
    public boolean isOrderDefined() {
        return !this.playersOrder.isEmpty();
    }
    public int getNumPlayers() {
        return this.players.size();
    }
    public int getPlayerSpaceId(Player player) {
        return this.board.getPlayerPos(player);
    }

    public ArrayList<Integer> getPlayersPositions() {
        ArrayList<Integer> positions = new ArrayList<>();
        for (Player player : this.players) {
            positions.add(this.board.getPlayerPos(player));
        }
        return positions;
    }

    public ArrayList<String> getPlayersColors() {
        ArrayList<String> colors = new ArrayList<>();
        for (Player player : this.players) {
            colors.add(player.getColor());
        }
        return colors;
    }

    public ArrayList<Integer> getPlayersSpaces(){
        ArrayList<Integer> spaces = new ArrayList<>();
        for (Player player : this.players) {
            spaces.add(this.board.getPlayerPos(player));
        }
        return spaces;
    }

    public ArrayList<Integer> getNumHouseinAllSpaces(){
        ArrayList<Integer> numHouse = new ArrayList<>();
        for (int i = 0; i < this.board.getBoardSize(); i++) {
            Space space = this.board.getSpace(i);
            if (space instanceof PropertySpace) {
                PropertySpace tempSpace = (PropertySpace) space;
                numHouse.add(tempSpace.getProperty().getNumHouses());
            }
            else {
                numHouse.add(0);
            }
        }
        return numHouse;
    }

    /*public Property getPropertyById(int id) {
        return this.board.getPropertyById(id);
    }*/
}
