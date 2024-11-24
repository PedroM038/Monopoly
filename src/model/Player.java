//package model;

import java.util.ArrayList;
import java.util.List;

import assets.*;

public class Player {
    private String name;
    Integer id;
    private String color;
    private int money;
    private List<Property> properties;
    private boolean isLocked;

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
        property.buy();
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
        for (Property property : properties) {
            totalAssets += property.getMortgagePrice();
        }
        return totalAssets;
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

    public List<Property> getProperties() {
        return this.properties;
    }

    public boolean isLocked() {
        return this.isLocked;
    }
}

