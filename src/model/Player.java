/*
 * The Player class represents a player from the game
 * It stores all data about the player, and handles the actions a player can do in a game
 * such as buy and sell properties
 */

//package model;

import java.util.ArrayList;

import assets.*;
import java.io.*;

public class Player implements Serializable {
    private String name;
    private Integer id;
    private String color;
    private Integer money;
    private ArrayList<Property> properties;
    private Boolean isLocked;

    public Player(String name, Integer id, String color, int initialMoney) {
        this.name = name;
        this.id = id;
        this.color = color;
        this.money = initialMoney;
        this.properties = new ArrayList<>();
        this.isLocked = false;;
    }

    public void buyProperty(Property property) {
        properties.add(property);
        property.buy(this.id);
    }

    public void sellProperty(Property property) {
        properties.remove(property);
        property.sell();
    }

    public void lock() {
        this.isLocked = true;
    }

    public void free() {
        this.isLocked = false;
    }

    // Método para calcular o valor total das propriedades possuídas
    public int getTotalAssets() {
        int totalAssets = this.money;
        for (Property property : this.properties) {
            totalAssets += property.getMortgagePrice();
        }
        return totalAssets;
    }

    public boolean isEliminated() {
        return this.getMoney() < 0;
    }

    // gains money
    public void remunerate(int money) {
        this.money += money;
    }

    // loses money
    public void pay(int money) {
        this.money -= money;
    }


    // GETTERS
    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getColor() {
        return this.color;
    }

    public int getMoney() {
        return this.money;
    }

    public ArrayList<Property> getProperties() {
        return this.properties;
    }

    public boolean isLocked() {
        return this.isLocked;
    }
}

