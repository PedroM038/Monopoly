//package model;

import assets.*;

public class Bank {
    public static boolean canBuyProperty(Player player, Property property) {
        return player.getMoney() >= property.getActualPrice();
    }

    public static void buyProperty(Player player, Property property) {
        player.pay(property.getActualPrice());
        player.buyProperty(property);
    }

    public static void buyHouse(Player player, Property property) {
        player.pay(property.getHousePrice());
        property.buyHouse();
    }

    public static void sellProperty(Player player, Property property) {
        player.sellProperty(property);
        player.remunerate(property.getActualPrice());
    }

    public static void payPlayer(Player player, int money) {
        player.remunerate(money);
    }

    public static void chargePlayer(Player player, int money) {
        player.pay(money);
    }

    public static void chargeRent(Player owner, Player tenant, Property property) {
        int rent = property.getRentValue();
        Bank.chargePlayer(tenant, rent);
        Bank.payPlayer(owner, rent);
    }

    // returns true if player gains money, false otherwise
    public static boolean applyCardToPlayer(Player player, Card card) {
        if (card.getType().equals("Sorte")) {
            player.remunerate(card.getAmount());
            return true;
        }
        else {
            player.pay(card.getAmount());
            return false;
        }
    }
}
