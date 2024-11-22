//package model;

import assets.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class Board {
    private Space[] spaces;
    private int[] playersPos;

    public Board(int boardSize, int numPlayers, int prisonId, int[] propertiesId, int[] cardsId, int[] moneyId) {
        this.spaces = new Space[boardSize];
        this.playersPos = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.playersPos[i] = 0;
        }
        for (int i = 0; i < boardSize; i++) {
            this.spaces[i] = new Space();
        }

        this.spaces[prisonId] = new PrisonSpace();

        Property[] properties = this.loadProperties("../../assets/properties.txt");
        for (int i = 0; i < propertiesId.size(); i++) {
            this.spaces[propertiesId[i]] = new PropertySpace(properties[pos]);
        }

        for (int i = 0; i < cardsId.size(); i++) {
            this.spaces[cardsId[i]] = new CardSpace();
        }

        MoneySpace[] moneySpaces = this.loadMoneySpaces("../../assets/money_spaces.txt");
        for (int i = 0; i < moneyId.size(); i++) {
            this.spaces[moneyId[i]] = moneySpaces[i];
        }
    }

    public Property[] loadProperties(String filename) {
        ArrayList<Property> properties = new ArrayList<Property>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
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
        return properties.toArray();
    }

    public MoneySpace[] loadMoneySpaces(String filename) {
        ArrayList<MoneySpace> moneySpaces = new ArrayList<MoneySpace>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String name = parts[0];
                int value = Integer.parseInt(parts[1]);

                moneySpaces.add(new MoneySpace(name, price));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());
        }
        return moneySpaces.toArray();
    }

    public void movePlayer(int playerId, int moves) {
        this.playersPos.set(playerId, (this.playersPos[playerId] + moves) % this.getBoardSize());
    }

    // if players moves, does it passes through the start space
    public boolean goesThroughStart(int playerId, int moves) {
        return this.playersPos[playerId] + moves >= this.getBoardSize();
    }

    public Space getPlayerSpace(int playerId) {
        return this.getSpace(this.playersPos[playerId]);
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
        return this.playersPos[playerId];
    }
}
