/*
 * The Board class represents the board from the game
 * It's stores data about the spaces and the player's positions
 * It's only actions are to move players and give information about the positions
 */

package codemodel;

import codemodel.assets.*;
import java.io.*;
import java.util.ArrayList;

public class Board implements Serializable {
    private ArrayList<Space> spaces;
    private ArrayList<Integer> playersPos;

    public Board(int boardSize, int numPlayers, int prisonId, ArrayList<Integer>propertiesId, ArrayList<Integer>cardsId, ArrayList<Integer>moneyId) {
        this.spaces = new ArrayList<Space>(boardSize);
        this.playersPos = new ArrayList<Integer>(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            this.playersPos.add(0);
        }
        for (int i = 0; i < boardSize; i++) {
            this.spaces.add(new Space());
        }
        this.spaces.set(prisonId, new PrisonSpace());
        ArrayList<Property> properties = this.loadProperties("/../../assets/properties.txt");
        for (int i = 0; i < propertiesId.size(); i++) {
            this.spaces.set(propertiesId.get(i), new PropertySpace(properties.get(i)));
        }
        for (Integer i: cardsId) {
            this.spaces.set(i, new CardSpace());
        }
        ArrayList<MoneySpace> moneySpaces = this.loadMoneySpace("/../../assets/money_spaces.txt");
        for (int i = 0; i < moneyId.size(); i++) {
            this.spaces.set(moneyId.get(i), moneySpaces.get(i));
        }
    }

    public ArrayList<Property> loadProperties(String filename) {
        ArrayList<Property> properties = new ArrayList<Property>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String name = parts[0];
                int price = Integer.parseInt(parts[1]);
                int baseRent = Integer.parseInt(parts[2]);
                int housePrice = Integer.parseInt(parts[3]);
                int houseValue = Integer.parseInt(parts[4]);
                int mortgagePrice = Integer.parseInt(parts[5]);

                properties.add(new Property(name, price, housePrice, houseValue, mortgagePrice, baseRent));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());
        }
        return properties;
    }

    public ArrayList<MoneySpace> loadMoneySpace(String filename) {
        ArrayList<MoneySpace> moneySpaces = new ArrayList<MoneySpace>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String name = parts[0];
                int value = Integer.parseInt(parts[1]);
                moneySpaces.add(new MoneySpace(name, value));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());
        }
        return moneySpaces;
    }

    public void movePlayer(Player player, int moves) {
        this.playersPos.set(player.getId(), (this.playersPos.get(player.getId()) + moves) % this.getBoardSize());
    }

    // if players moves, does it passes through the start space
    public boolean goesThroughStart(Player player, int moves) {
        return this.playersPos.get(player.getId()) + moves >= this.getBoardSize();
    }

    public ArrayList<Property> getProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        for (int i = 0; i < this.spaces.size(); i++) {
            if (this.spaces.get(i) instanceof PropertySpace) {
                PropertySpace tempSpace = (PropertySpace) this.spaces.get(i);
                properties.add(tempSpace.getProperty()); 
            }
        }
        return properties;
    }

    public Space getPlayerSpace(Player player) {
        return this.getSpace(this.playersPos.get(player.getId()));
    }

    public Integer getPlayerSpaceId(Player player) {
        return this.playersPos.get(player.getId());
    }

    public Space getSpace(int id) {
        if (id >= 0 && id < spaces.size()) {
            return spaces.get(id);
        }
        throw new IllegalArgumentException("Invalid board position.");
    }

    public int getBoardSize() {
        return spaces.size();
    }

    public int getPlayerPos(Player player) {
        return this.playersPos.get(player.getId());
    }
}
