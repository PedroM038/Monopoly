package model;

import assets.*;

public class Bank {
    public Bank() {
    }

    public boolean canBuyProperty(Player player, Property property) {
        return player.getMoney() >= property.getActualPrice();
    }

    public void buyProperty(Player player, Property property) {
        player.pay(property.getActualPrice())
        player.buy(property);
    }

    public void sellProperty(Player player, Property property) {
        player.sellProperty(Property);
        player.remunerate(property.getActualPrice());
    }

    public void payPlayer(Player player, int money) {
        player.remunerate(money);
    }

    public void chargePlayer(Player player, int money) {
        player.pay(money);
    }

    // returns true if player gains money, false otherwise
    public boolean applyCardToPlayer(Player player, Card card) {
        if (card.getType().equals("Sorte")) {
            player.remunerate(card.getAmount());
        }
        else {
            player.pay(card.getAmount());
        }
    }
}
