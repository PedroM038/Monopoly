package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int money;
    private List<Property> properties;
    private boolean inJail;
    private int position;  

    public Player(String name, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.properties = new ArrayList<>();
        this.inJail = false;
        this.position = 0; //initial space
    }

    public boolean buyProperty(Property property) {
        if (this.money >= property.getActualPrice() && property.buy(this)) {
            properties.add(property);
            return true;
        }
        return false;
    }

    public boolean sellProperty(Property property) {
        if (properties.contains(property) && property.sell()) {
            properties.remove(property);
            return true;
        }
        return false;
    }
 
    public boolean isInJail() {
        return inJail;
    }

    // Método para calcular o valor total das propriedades possuídas
    public int getTotalAssets() {
        int totalAssets = this.money;
        for (Property property : properties) {
            totalAssets += property.getPrice();
        }
        return totalAssets;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public int getPosition() {
        return position;
    }

    // Método para exibir as propriedades possuídas
    public void showProperties() {
        System.out.println(name + "'s Properties:");
        for (Property property : properties) {
            System.out.println("- " + property.getName());
        }
    }
}

