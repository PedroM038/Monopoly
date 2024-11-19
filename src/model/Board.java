//package model;

import assets.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class Board {
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
        // TO-DO: open file and stuff
        ArrayList<Property> properties = this.loadProperties("../../assets/properties.txt");
        for (int pos = 0; pos < propertiesId.size(); pos++) {
            this.spaces.set(propertiesId.get(pos), new PropertySpace(properties.get(pos)));
        }
        for (Integer i: cardsId) {
            this.spaces.set(i, new CardSpace());
        }
        for (Integer i: moneyId) {
            this.spaces.set(i, new MoneySpace("AAAAAAAA", 50));
        }
    }

    public ArrayList<Property> loadProperties(String filename) {
        ArrayList<Property> properties = new ArrayList<Property>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // String name, int price, int housePrice, int houseValue, int mortgagePrice, int baseRent
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String name = parts[0];
                int price = Integer.parseInt(parts[1]);
                int housePrice = Integer.parseInt(parts[2]);
                int houseValue = Integer.parseInt(parts[3]);
                int mortgagePrice = Integer.parseInt(parts[4]);
                int baseRent = Integer.parseInt(parts[5]);

                properties.add(new Property(name, price, housePrice, houseValue, mortgagePrice, baseRent));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());
        }
        return properties;
    }

    public void movePlayer(int playerId, int moves) {
        this.playersPos.set(playerId, (this.playersPos.get(playerId) + moves) % this.getBoardSize());
    }

    // if players moves, does it passes through the start space
    public boolean goesThroughStart(int playerId, int moves) {
        return this.playersPos.get(playerId) + moves >= this.getBoardSize();
    }

    public Space getPlayerSpace(int playerId) {
        return this.getSpace(this.playersPos.get(playerId));
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

    public int getPlayerPos(int playerId) {
        return this.playersPos.get(playerId);
    }
}
