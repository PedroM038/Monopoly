package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int id;
    private int money;
    private List<Property> properties;
    private int jailTime;  // how  much time should be in jail

    public Player(String name, int id, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.properties = new ArrayList<>();
        this.jailTime = 0;
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

    public void receiveCard(Card card) {
        if card.getType().equals("Sorte") {
            this.money = Math.max(0, this.money - card.getAmount());
        }
        else {
            this.money += card.getAmount();
        }
    }
 
    public boolean isInJail() {
        return this.jailTime != 0;
    }

    // Método para calcular o valor total das propriedades possuídas
    public int getTotalAssets() {
        int totalAssets = this.money;
        for (Property property : properties) {
            totalAssets += property.getPrice();
        }
        return totalAssets;
    }


    // GETTERS
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Property> getProperties() {
        return properties;
    }
}

